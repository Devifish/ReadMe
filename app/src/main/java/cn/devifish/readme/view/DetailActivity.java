package cn.devifish.readme.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import cn.devifish.readme.R;
import cn.devifish.readme.dao.BookListDao;
import cn.devifish.readme.dao.BookmarkDao;
import cn.devifish.readme.entity.Book;
import cn.devifish.readme.entity.Chapter;
import cn.devifish.readme.entity.BookDetail;
import cn.devifish.readme.provider.BookProvider;
import cn.devifish.readme.util.ProviderUtil;
import cn.devifish.readme.view.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zhang on 2017/2/22.
 *
 * @author zhang
 */

public class DetailActivity extends BaseActivity {

    private static final int CHAPTER_LATELY = 10;
    private BookListDao mBookListDao;
    private BookmarkDao mBookmarkDao;
    private BookDetail mBookDetail;

    @BindView(R.id.book_title) TextView mTitle;
    @BindView(R.id.book_author) TextView mAuthor;
    @BindView(R.id.book_image) ImageView mImage;
    @BindView(R.id.tools_image) ImageView mToolsImage;
    @BindView(R.id.book_btn) Button mButton;
    @BindView(R.id.lastRead) TextView mLastRead;
    @BindView(R.id.book_info) TextView mInfo;
    @BindView(R.id.updateTime) TextView mUpdateTime;
    @BindView(R.id.chapterLength) TextView mChapterLength;
    @BindView(R.id.chapter_list) ListView mListView;
    @BindView(R.id.progress) ProgressBar mProgressBar;
    @BindView(R.id.chapterAll) Button mChapterAll;
    @BindView(R.id.book_detail) LinearLayout mDetail;

    @Override
    protected int bindLayout() {return R.layout.activity_detail;}

    @Override
    protected void initVar() {
        mBookmarkDao = new BookmarkDao(this);
        mBookListDao = new BookListDao(this);
        Book book = getIntent().getParcelableExtra("book_info");

        if (book != null) {
            if (book.getTitle() != null && book.getAuthor() != null) {
                mTitle.setText(book.getTitle());
                mAuthor.setText(book.getAuthor());
            }
            if (book.getImagePath() != null) {
                Glide.with(DetailActivity.this)
                        .load(book.getImagePath())
                        .dontAnimate()
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .into(mImage);
            }
            if (book.getBookPath() != null) {
                new DetailDataInit().execute(book);
            }
        }
    }

    @Override
    protected void initView() {
        /* 绑定Support库工具栏 */
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /* ActionBar 添加返回键 */
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);

        mDetail.setVisibility(View.INVISIBLE);
        mToolsImage.setVisibility(View.INVISIBLE);
        mProgressBar.setVisibility(View.VISIBLE);
        mListView.setVisibility(View.GONE);
    }

    @OnClick(R.id.book_btn) void onReadBook() {
        if (mBookDetail != null) {
            switch (mButton.getText().toString()) {
                case "加入书架":
                    if (mBookListDao.insert(mBookDetail.getBook())) {
                        mButton.setText(R.string.book_start);
                        Toast.makeText(DetailActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(DetailActivity.this, "添加失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case "开始阅读":
                case "继续阅读":
                    startReadActivity(mBookDetail);
                    break;
                default: break;
            }
        }
    }

    @OnClick(R.id.chapterAll) void onShowChapterList() {
        if (mBookDetail != null) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(DetailActivity.this);
            dialog.setTitle(mBookDetail.getBook().getTitle());
            dialog.setItems(mBookDetail.getChapterNameArray(), (dialog1, which) -> {
                BookDetail temp = mBookDetail;
                temp.setBookmarkIndex(temp.getChapterList().size() - which - 1);
                startReadActivity(temp);
            });
            dialog.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.menu_search:
                startActivity(new Intent(this, SearchActivity.class));
                break;
            case R.id.menu_del:
                boolean isOK = mBookListDao.delete(mBookDetail.getBook());
                Toast.makeText(DetailActivity.this, isOK ? "删除成功" : "该书未加入书架", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mBookDetail != null) {
            mProgressBar.setVisibility(View.GONE);
            mListView.setVisibility(View.INVISIBLE);
            mListView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBookListDao.close();
        mBookmarkDao.close();
    }

    /**
     * 异步线程加载书籍信息
     */
    @SuppressLint("StaticFieldLeak")
    private class DetailDataInit extends AsyncTask<Book, Integer, BookDetail> {

        @Override
        protected BookDetail doInBackground(Book... books) {
            Book book = books[0];
            //从小说源网站加载内容
            BookProvider provider = ProviderUtil.Builder(ProviderUtil.PROVIDER_8DUSHU).getBookProvider(book.getBookPath());
            BookDetail detail = new BookDetail();
            if (provider != null) {
                //书籍详情，章节信息,封面图,书签
                detail.setChapterList(provider.getChapterList());
                detail.setBookInfo(provider.getBookInfo());
                book.setImagePath(provider.getBookImagePath());
                detail.setBook(book);
                detail.setBookmark(mBookmarkDao.getAutoBookmark(book.getId()));
            }
            Log.i("bookDetail_info",detail.toString());
            return detail;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(BookDetail bookDetail) {
            super.onPostExecute(bookDetail);

            /* 页面内容加载 */
            Book book = bookDetail.getBook();
            List<Chapter> list = bookDetail.getChapterList();
            mInfo.setText(bookDetail.getBookInfo());
            if (mImage.getDrawable() == null) {
                Glide.with(DetailActivity.this)
                        .load(book.getImagePath())
                        .dontAnimate()
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .into(mImage);
            }

            if (bookDetail.getBookmarkIndex() != 0) mLastRead.setText(list.get(bookDetail.getBookmarkIndex()).getName());
            else mLastRead.setText(R.string.book_noRead);
            mChapterLength.setText(String.format("共%s章",list.size()));

            /* 阅读按钮加载 */
            boolean isExists = mBookListDao.exists(bookDetail.getBook());
            if (isExists) {
                if (bookDetail.getBookmarkIndex() == 0) mButton.setText(R.string.book_start);
                else mButton.setText(R.string.book_continue);
            }else mButton.setText(R.string.book_add);

            //展示最近5章小说标题
            mListView.setAdapter(new ArrayAdapter<>(
                    DetailActivity.this,
                        R.layout.chapter_item_detail,
                            getChapterLastItem(list, CHAPTER_LATELY)));

            mListView.setOnItemClickListener((parent, view, position, id) -> {
                mBookDetail.setBookmarkIndex(mBookDetail.getChapterList().size() - position - 1);
                startReadActivity(mBookDetail);
            });

            /* 显示隐藏内容 */
            mToolsImage.setVisibility(View.VISIBLE);
            mProgressBar.setVisibility(View.GONE);
            mDetail.setVisibility(View.VISIBLE);
            mListView.setVisibility(View.VISIBLE);

            ViewAnimationUtils.createCircularReveal(
                    mToolsImage,
                    mToolsImage.getWidth()/2,
                    mToolsImage.getHeight()/2,
                    0,
                    mToolsImage.getWidth()
            ).setDuration(1000).start();

            /* 传递内容至各个事件 */
            mBookDetail= bookDetail;
        }
    }

    /**
     * 倒序截取给定长度的List
     *
     * @param chapterList 章节集合
     * @param length 截取长度
     * @return 你懂的
     */
    private List<Chapter> getChapterLastItem(List<Chapter> chapterList, int length) {
        List<Chapter> temp = new ArrayList<>();
        if (chapterList != null) {
            int index = chapterList.size() - 1;
            length = length > chapterList.size() ? chapterList.size() : length;
            for (int i = index; i > index - length; i--) temp.add(chapterList.get(i));
        }
        return temp;
    }

    private void startReadActivity(BookDetail bookDetail) {
        Intent intent = new Intent(DetailActivity.this, ReadActivity.class);
        intent.putExtra("chapter_detail", bookDetail);
        startActivity(intent);
    }
}

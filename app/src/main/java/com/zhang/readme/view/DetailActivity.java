package com.zhang.readme.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhang.readme.R;
import com.zhang.readme.dao.BookListDao;
import com.zhang.readme.dao.BookmarkDao;
import com.zhang.readme.entity.Book;
import com.zhang.readme.entity.Bookmark;
import com.zhang.readme.entity.Chapter;
import com.zhang.readme.entity.BookDetail;
import com.zhang.readme.provider.BookProvider;
import com.zhang.readme.util.ProviderUtil;
import com.zhang.readme.util.FileCacheUtil;
import com.zhang.readme.view.base.BaseActivity;

import org.w3c.dom.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends BaseActivity {

    /** 显示最近章节数的长度值 */
    private static final int CHAPTER_LATELY = 10;

    private BookListDao mBookListDao;
    private BookmarkDao mBookmarkDao;
    private Book mBook;
    private BookDetail mBookDetail;

    private TextView mTitle;
    private TextView mAuthor;
    private ImageView mImage;
    private Button mButton;
    private TextView mLastRead;
    private TextView mInfo;
    private TextView mUpdateTime;
    private TextView mChapterLength;
    private ListView mListView;
    private ProgressBar mProgressBar;
    private Button mChapterAll;
    private NestedScrollView mScrollView;

    @Override
    protected void initVar() {
        mBookmarkDao = new BookmarkDao(this);
        mBookListDao = new BookListDao(this);
        mBook = getIntent().getParcelableExtra("book_info");
        if (mBook != null) {
            String bookPath = mBook.getBookPath();
            if (bookPath != null && bookPath.length() > 0) {
                new DetailDataInit().execute(mBook);
            }
        }
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_detail);

        /* 绑定Support库工具栏 */
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        /* ActionBar 添加返回键 */
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);

        /* View初始化 */
        mTitle = (TextView) findViewById(R.id.detail_book_title);
        mAuthor = (TextView) findViewById(R.id.detail_book_author);
        mImage = (ImageView) findViewById(R.id.detail_book_image);
        mInfo = (TextView) findViewById(R.id.detail_book_info);
        mButton = (Button) findViewById(R.id.detail_book_btn);
        mLastRead = (TextView) findViewById(R.id.detail_lastRead);
        mChapterLength = (TextView) findViewById(R.id.detail_chapterLength);
        mUpdateTime = (TextView) findViewById(R.id.detail_updateTime);
        mChapterAll = (Button) findViewById(R.id.detail_chapterAll);
        mListView = (ListView) findViewById(R.id.detail_chapter_list);
        mProgressBar = (ProgressBar) findViewById(R.id.detail_book_progress);
        mScrollView = (NestedScrollView) findViewById(R.id.detail_view);
    }

    @Override
    protected void initViewState() {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        });

        mChapterAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBookDetail != null) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(DetailActivity.this);
                    dialog.setTitle(mBookDetail.getBook().getTitle());
                    dialog.setItems(mBookDetail.getChapterNameArray(), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            BookDetail temp = mBookDetail;
                            temp.setBookmarkIndex(temp.getChapterList().size() - which - 1);
                            startReadActivity(temp);
                        }
                    });
                    dialog.show();
                }
            }
        });

        mScrollView.setVisibility(View.INVISIBLE);
        mProgressBar.setVisibility(View.VISIBLE);
        mListView.setVisibility(View.GONE);
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
                boolean b = mBookListDao.delete(mBook);
                Toast.makeText(DetailActivity.this, b ? "删除成功" : "该书未加入书架", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
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
                FileCacheUtil.getFileByURL(DetailActivity.this, provider.getBookImagePath());
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
            mTitle.setText(book.getTitle());
            mAuthor.setText(String.format("作者：%s", book.getAuthor()));
            File file = FileCacheUtil.getFileByURL(DetailActivity.this ,book.getImagePath());
            mImage.setImageDrawable(Drawable.createFromPath(file.getAbsolutePath()));
            mInfo.setText(bookDetail.getBookInfo());
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

            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    mBookDetail.setBookmarkIndex(mBookDetail.getChapterList().size() - position - 1);
                    startReadActivity(mBookDetail);
                }
            });

            /* 显示隐藏内容 */
            mProgressBar.setVisibility(View.GONE);
            mScrollView.setVisibility(View.VISIBLE);
            mListView.setVisibility(View.VISIBLE);

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
        List<Chapter> temp = new ArrayList<Chapter>();
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

package com.zhang.readme.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
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
import android.widget.TextView;
import android.widget.Toast;

import com.zhang.readme.R;
import com.zhang.readme.dao.BookListDao;
import com.zhang.readme.entity.Book;
import com.zhang.readme.entity.ChapterList;
import com.zhang.readme.entity.BookDetail;
import com.zhang.readme.provider.BookProvider;
import com.zhang.readme.util.ProviderUtil;
import com.zhang.readme.util.FileCacheUtil;

import java.io.File;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener{

    /** 显示最近章节数的长度值 */
    private static final int CHAPTER_LATELY = 5;

    private BookListDao bookListDao;
    private Book book;
    private BookDetail bookDetail;

    private TextView title;
    private TextView author;
    private ImageView image;
    private Button button;
    private TextView info;
    private ListView listView;
    private ProgressBar progressBar;
    private NestedScrollView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        /* 绑定Support库工具栏 */
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        CollapsingToolbarLayout ctl = (CollapsingToolbarLayout) findViewById(R.id.detail_collapsing);
        ctl.setExpandedTitleColor(0x00ffffff); //隐藏CollapsingToolBar展开的的文字
        setSupportActionBar(toolbar);
        /* ActionBar 添加返回键 */
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);
        /* 获取内容信息 */
        bookListDao = new BookListDao(this);
        book = getIntent().getParcelableExtra("book_info");
        if (book != null) {
            initView();
            String bookpath = book.getBookPath();
            if (bookpath != null && bookpath.length() > 0) {
                new DetailDataInit().execute(book);
            }
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
                boolean b = bookListDao.delete(book);
                Toast.makeText(DetailActivity.this, b ? "删除成功" : "该书未加入书架", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * 初始化View对象
     */
    private void initView() {
        title = (TextView) findViewById(R.id.detail_book_title);
        author = (TextView) findViewById(R.id.detail_book_author);
        image = (ImageView) findViewById(R.id.detail_book_image);
        info = (TextView) findViewById(R.id.detail_book_info);
        button = (Button) findViewById(R.id.detail_book_btn);
        TextView chapterAll = (TextView) findViewById(R.id.detail_chapterAll);
        listView = (ListView) findViewById(R.id.detail_chapter_list);
        progressBar = (ProgressBar) findViewById(R.id.detail_book_progress);
        view = (NestedScrollView) findViewById(R.id.detail_view);

        //初始化View状态
        button.setOnClickListener(this);
        chapterAll.setOnClickListener(this);
        view.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        listView.setVisibility(View.GONE);
    }

    /**
     * View内格个按钮的点击事件逻辑
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.detail_book_btn:
                if (bookDetail != null) {
                    switch (button.getText().toString()) {
                        case "加入书架":
                            if (bookListDao.insert(bookDetail.getBook())) {
                                button.setText(R.string.book_start);
                                Toast.makeText(DetailActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(DetailActivity.this, "添加失败", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case "开始阅读":
                        case "继续阅读":
                            startReadActivity(bookDetail);
                            break;
                        default: break;
                    }
                }
                break;
            case R.id.detail_chapterAll:
                if (bookDetail != null) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(DetailActivity.this);
                    dialog.setTitle(bookDetail.getBook().getTitle());
                    dialog.setItems(bookDetail.getChapterNameArray(), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            BookDetail temp = bookDetail;
                            temp.setReadProgress(temp.getChapterList().size() - which - 1);
                            startReadActivity(temp);
                        }
                    });
                    dialog.show();
                }
                break;
            default: break;
        }
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
                //书籍详情，章节信息,封面图
                detail.setChapterList(provider.getChapterList());
                detail.setBookInfo(provider.getBookInfo());
                book.setImagePath(provider.getBookImagePath());
                detail.setBook(book);
                Log.i("image_cache", provider.getBookImagePath());
                FileCacheUtil.getFileByURL(DetailActivity.this, provider.getBookImagePath());
            }
            return detail;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(BookDetail detail) {
            super.onPostExecute(detail);
            initViewState(detail);
            //展示最近5章小说标题
            ChapterList list = detail.getChapterList();
            listView.setAdapter(new ArrayAdapter<>(
                    DetailActivity.this,
                        R.layout.chapter_item_detail,
                            getChapterLastItem(list, CHAPTER_LATELY)));

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    bookDetail.setReadProgress(bookDetail.getChapterList().size() - position - 1);
                    startReadActivity(bookDetail);
                }
            });
        }
    }

    /**
     * 倒序截取给定长度的List
     *
     * @param chapterList 章节集合
     * @param length 截取长度
     * @return 你懂的
     */
    private ChapterList getChapterLastItem(ChapterList chapterList, int length) {
        ChapterList temp = new ChapterList();
        if (chapterList != null) {
            int index = chapterList.size() - 1;
            length = length > chapterList.size() ? chapterList.size() : length;
            for (int i = index; i > index - length; i--) temp.add(chapterList.get(i));
        }
        return temp;
    }

    /**
     * 设置各个View的状态
     */
    private void initViewState(BookDetail bookDetail) {
        //页面内容加载
        Book book = bookDetail.getBook();
        title.setText(book.getTitle());
        author.setText(String.format("作者：%s", book.getAuthor()));
        File file = FileCacheUtil.getFileByURL(DetailActivity.this ,book.getImagePath());
        image.setImageDrawable(Drawable.createFromPath(file.getAbsolutePath()));
        info.setText(bookDetail.getBookInfo());
        //阅读按钮加载
        boolean isExists = bookListDao.exists(bookDetail.getBook());
        if (isExists) {
            if (bookDetail.getReadProgress() == 0) button.setText(R.string.book_start);
            else button.setText(R.string.book_continue);
        }else button.setText(R.string.book_add);

        //显示隐藏内容
        progressBar.setVisibility(View.GONE);
        view.setVisibility(View.VISIBLE);
        listView.setVisibility(View.VISIBLE);
        //传递内容至各个事件
        this.bookDetail = bookDetail;
    }

    private void startReadActivity(BookDetail bookDetail) {
        Intent intent = new Intent(DetailActivity.this, ReadActivity.class);
        intent.putParcelableArrayListExtra("chapter_list", bookDetail.getChapterList());
        intent.putExtra("chapter_index", bookDetail.getReadProgress());
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bookListDao.close();
    }

}

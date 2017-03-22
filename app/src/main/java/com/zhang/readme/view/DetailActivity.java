package com.zhang.readme.view;

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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zhang.readme.R;
import com.zhang.readme.dao.BooksDao;
import com.zhang.readme.model.Book;
import com.zhang.readme.model.ChapterList;
import com.zhang.readme.model.BookDetail;
import com.zhang.readme.provider.BookProvider;
import com.zhang.readme.util.FileCacheUtil;

import java.io.File;

public class DetailActivity extends AppCompatActivity {

    private BooksDao dao;
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

        //绑定Support库工具栏
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        CollapsingToolbarLayout ctl = (CollapsingToolbarLayout) findViewById(R.id.detail_collapsing);
        ctl.setExpandedTitleColor(0x00ffffff);
        setSupportActionBar(toolbar);
        //ActionBar 添加返回键
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);
        //获取内容信息
        dao = new BooksDao(this);
        Book book = getIntent().getParcelableExtra("book_info");
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
                startActivity(new Intent(this, ReadActivity.class));
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
        chapterAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bookDetail != null) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(DetailActivity.this);
                    dialog.setTitle(bookDetail.getBook().getTitle());
                    dialog.setItems(bookDetail.getChapterNameArray(), null);
                    dialog.show();
                }
            }
        });
    }

    /**
     * 异步线程加载书籍信息
     */
    private class DetailDataInit extends AsyncTask<Book, Integer, BookDetail> {

        @Override
        protected BookDetail doInBackground(Book... books) {
            Book book = books[0];
            BookProvider provider = BookProvider.Builder(book.getBookPath(), BookProvider.PROVIDER_8DUSHU);
            BookDetail detail = new BookDetail();
            if (provider != null) {
                //书籍详情，章节信息,封面图
                detail.setChapterList(provider.getChapterList());
                detail.setBookInfo(provider.getBookInfo());
                book.setImagePath(provider.getBookImagePath());
                detail.setBook(book);
                //缓存封面图
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
            Book book = detail.getBook();
            bookDetail = detail;
            //view内容加载
            title.setText(book.getTitle());
            author.setText(String.format("作者：%s", book.getAuthor()));
            File file = FileCacheUtil.getFileByURL(DetailActivity.this ,book.getImagePath());
            image.setImageDrawable(Drawable.createFromPath(file.getAbsolutePath()));
            info.setText(detail.getBookInfo());
            //展示最近5章小说标题
            ChapterList list = detail.getChapterList();
            listView.setAdapter(new ArrayAdapter<>(
                    DetailActivity.this,
                        R.layout.chapter_item_detail,
                            getChaterLastItem(list, 5)));
            //加载添加到书架，开始阅读，继续阅读等按钮状态
            initButton();
            //显示隐藏内容
            progressBar.setVisibility(View.GONE);
            view.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 倒序截取给定长度的List
     *
     * @param chapterList 章节集合
     * @param length 截取长度
     * @return 你懂的
     */
    private ChapterList getChaterLastItem(ChapterList chapterList, int length) {
        ChapterList temp = new ChapterList();
        if (chapterList != null) {
            int index = chapterList.size() - 1;
            length = length > chapterList.size() ? chapterList.size() : length;
            for (int i = index; i > index - length; i--) temp.add(chapterList.get(i));
        }
        return temp;
    }

    /**
     * 该按钮存在三种状态
     * 添加到书架，开始阅读，继续阅读
     * 通过book信息进行判断
     */
    private void initButton() {
        boolean isExists = dao.exists(bookDetail.getBook());
        if (isExists) {
            if (bookDetail.getReadProgress() == 0) button.setText(R.string.book_start);
            else button.setText(R.string.book_continue);
        }else button.setText(R.string.add_book);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                switch (((Button) v).getText().toString()) {
					case "加入书架":
						dao.insert(bookDetail.getBook());
					    break;
					case "开始阅读":
					case "继续阅读":
                        intent = new Intent(DetailActivity.this, ReadActivity.class);
                        intent.putParcelableArrayListExtra("chapter_list", bookDetail.getChapterList());
                        intent.putExtra("chapter_index", bookDetail.getReadProgress());
                        startActivity(intent);
						break;
				}
            }
        });
    }
}

package com.zhang.readme.ui;

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
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zhang.readme.R;
import com.zhang.readme.model.BookInfo;
import com.zhang.readme.model.ChapterInfo;
import com.zhang.readme.tools.BookReader;
import com.zhang.readme.tools.FileCacheUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private TextView title;
    private TextView author;
    private ImageView image;
    private TextView detail;
    private BookInfo info;
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
        info = (BookInfo) getIntent().getExtras().get("book_info");
        if (info != null) {
            initView();
            String bookpath = info.getBookPath();
            if (bookpath != null && bookpath.length() > 0) {
                new DetailDataInit().execute(bookpath);
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
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initView() {
        title = (TextView) findViewById(R.id.detail_book_title);
        author = (TextView) findViewById(R.id.detail_book_author);
        image = (ImageView) findViewById(R.id.detail_book_image);
        detail = (TextView) findViewById(R.id.detail_book_detail);
        TextView chapterAll = (TextView) findViewById(R.id.detail_chapterAll);
        listView = (ListView) findViewById(R.id.detail_chapter_list);
        progressBar = (ProgressBar) findViewById(R.id.detail_book_progress);
        view = (NestedScrollView) findViewById(R.id.detail_view);

        chapterAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailActivity.this);
                builder.setTitle(info.getTitle());
                builder.setItems(info.getChapterNameArray(), null);
                builder.show();
            }
        });
    }

    private void initPageData(BookInfo info) {
        //view内容加载
        title.setText(info.getTitle());
        author.setText(info.getAuthor());
        image.setImageDrawable(Drawable.createFromPath(info.getImagePath()));
        detail.setText(info.getDetail());
        //展示最近5章小说标题
        List<ChapterInfo> list = info.getChapter();
        listView.setAdapter(new ArrayAdapter<>(
                DetailActivity.this,
                    R.layout.chapter_item_detail,
                        getChaterLastItem(list, 5)));
    }

    /**
     * 倒序截取给定长度的List
     *
     * @param list   章节集合
     * @param length 截取长度
     * @return 你懂的
     */
    private List<ChapterInfo> getChaterLastItem(List<ChapterInfo> list, int length) {
        List<ChapterInfo> temp = new ArrayList<>();
        int index = list.size() - 1;
        length = length > list.size() ? list.size() : length;
        for (int i = index; i > index - length; i--) {
            temp.add(list.get(i));
        }
        return temp;
    }

    /**
     * 异步线程加载书籍信息
     *
     * @author zhang
     */
    private class DetailDataInit extends AsyncTask<String, Integer, BookInfo> {

        @Override
        protected BookInfo doInBackground(String... params) {
            BookReader br = new BookReader(params[0]);

            //书籍详情，章节信息,封面图
            BookInfo temp = info;
            temp.setChapter(br.getChapterList());
            temp.setDetail(br.getBookDetail());
            File image = FileCacheUtil.getFileByURL(DetailActivity.this, br.getBookImagePath());
            if (image != null) {
                temp.setImagePath(image.getAbsolutePath());
            }
            return temp;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(BookInfo info) {
            super.onPostExecute(info);
            initPageData(info);
            progressBar.setVisibility(View.GONE);
            view.setVisibility(View.VISIBLE);
        }
    }
}

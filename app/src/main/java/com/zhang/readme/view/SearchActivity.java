package com.zhang.readme.view;

import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;

import com.zhang.readme.R;
import com.zhang.readme.entity.Book;
import com.zhang.readme.entity.BookList;
import com.zhang.readme.provider.ChapterProvider;
import com.zhang.readme.util.ProviderUtil;

public class SearchActivity extends AppCompatActivity {

    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Toolbar toolbar = (Toolbar) findViewById(R.id.search_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);

        searchView = (SearchView) findViewById(R.id.search_searchView);
        searchView.onActionViewExpanded(); //设置搜索栏为展开状态

        new SearchDataInit().execute("我爱吃西红柿");

    }

    private class SearchDataInit extends AsyncTask<String, Integer, BookList> {

        @Override
        protected BookList doInBackground(String... strings) {
            Log.i("text_path", strings[0]);
            BookList books = ProviderUtil.Builder(ProviderUtil.PROVIDER_8DUSHU).getSearchProvider(strings[0]).getSearchResult();
            return books;
        }

        @Override
        protected void onPostExecute(BookList bookList) {
            super.onPostExecute(bookList);
            for (Book book : bookList) {
                Log.i("text", book.getTitle() + "--"+ book.getAuthor() +"--"+ book.getBookPath());
            }


        }
    }
}
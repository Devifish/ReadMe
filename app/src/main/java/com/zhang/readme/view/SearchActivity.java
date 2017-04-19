package com.zhang.readme.view;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import com.zhang.readme.R;
import com.zhang.readme.entity.Book;
import com.zhang.readme.entity.BookList;
import com.zhang.readme.provider.SearchProvider;
import com.zhang.readme.util.ProviderUtil;
import com.zhang.readme.view.base.BaseActivity;

import butterknife.BindView;

public class SearchActivity extends BaseActivity {

    @BindView(R.id.searchView) SearchView mSearchView;
    @BindView(R.id.list) ListView mListView;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;

    @Override
    protected int bindLayout() {return R.layout.activity_search;}

    @Override
    protected void initView() {
        /* 绑定Support库的Toolbar */
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);

        mSearchView.onActionViewExpanded(); //设置搜索栏为展开状态
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mProgressBar.setVisibility(View.VISIBLE);
                new SearchDataInit().execute(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    protected void initVar() {

    }

    private class SearchDataInit extends AsyncTask<String, Integer, BookList> {

        @Override
        protected BookList doInBackground(String... strings) {
            Log.i("text_path", strings[0]);
            SearchProvider provider = ProviderUtil.Builder(ProviderUtil.PROVIDER_8DUSHU).getSearchProvider(strings[0]);
            BookList books = provider.getSearchResult();
            return books;
        }

        @Override
        protected void onPostExecute(final BookList bookList) {
            super.onPostExecute(bookList);
            for (Book book : bookList) {
                Log.i("text", book.getTitle() + "--"+ book.getAuthor() +"--"+ book.getBookPath());
            }
            mProgressBar.setVisibility(View.GONE);
            mListView.setAdapter(new SearchListViewAdapter(bookList));
            mListView.setOnItemClickListener((parent, view, position, id) -> {
                Intent intent = new Intent(SearchActivity.this, DetailActivity.class);
                intent.putExtra("book_info", bookList.get(position));
                startActivity(intent);
            });
        }
    }

    private class SearchListViewAdapter extends BaseAdapter {

        private BookList mBooks;

        private SearchListViewAdapter(BookList books) {
            this.mBooks = books;
        }

        @Override
        public int getCount() {
            return mBooks.size();
        }

        @Override
        public Object getItem(int position) {
            return mBooks.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = LayoutInflater.from(SearchActivity.this).inflate(R.layout.book_item_search, parent, false);
            ImageView image = (ImageView) view.findViewById(R.id.search_image);
            TextView title = (TextView) view.findViewById(R.id.search_title);
            TextView author = (TextView) view.findViewById(R.id.search_author);

            Book book = mBooks.get(position);
            title.setText(book.getTitle());
            author.setText(book.getAuthor());

            return view;
        }
    }
}
package com.zhang.readme.view;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.zhang.readme.R;
import com.zhang.readme.entity.Chapter;
import com.zhang.readme.provider.ChapterProvider;
import com.zhang.readme.util.ProviderUtil;
import com.zhang.readme.view.base.BaseActivity;

import java.util.ArrayList;

public class ReadActivity extends BaseActivity {

    private ArrayList<Chapter> mChapters;
    private int mIndex;
    private TextView mTitle;
    private TextView mText;
    private ProgressBar mProgressBar;
    private ScrollView mScrollView;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_read);
        mTitle = (TextView) findViewById(R.id.read_title);
        mText = (TextView) findViewById(R.id.read_text);
        mProgressBar = (ProgressBar) findViewById(R.id.read_progressBar);
        mScrollView = (ScrollView) findViewById(R.id.read_context_layout);
    }

    @Override
    protected void initViewState() {
        mScrollView.setVisibility(View.INVISIBLE);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initVar() {
        mChapters = this.getIntent().getParcelableArrayListExtra("chapter_list");
        mIndex = this.getIntent().getIntExtra("chapter_index", -1);
        new ChapterDataInit().execute(mChapters.get(mIndex).getUrl());
    }

    private class ChapterDataInit extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... strings) {
            Log.i("text_path", strings[0]);
            ChapterProvider provider = ProviderUtil.Builder(ProviderUtil.PROVIDER_8DUSHU).getChapterProvider(strings[0]);
            return provider.getBookChapterText();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            mTitle.setText(mChapters.get(mIndex).getName());
            mText.setText(s);

            mScrollView.setVisibility(View.VISIBLE);
            mProgressBar.setVisibility(View.GONE);
        }
    }
}

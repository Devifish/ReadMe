package com.zhang.readme.view;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.zhang.readme.R;
import com.zhang.readme.model.Chapter;
import com.zhang.readme.model.ChapterList;

import java.util.ArrayList;

public class ReadActivity extends AppCompatActivity {

    private ArrayList<Chapter> list;
    private int index;
    private TextView title;
    private TextView text;
    private TextView progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        //获取内容信息
        list = this.getIntent().getParcelableArrayListExtra("chapter_list");
        index = this.getIntent().getIntExtra("chapter_index", -1);
        initView();
    }

    /**
     * 初始化View对象
     */
    private void initView() {
        title = (TextView) findViewById(R.id.read_title);
        text = (TextView) findViewById(R.id.read_text);
        progress = (TextView) findViewById(R.id.read_progress);

        title.setText(list.get(index).getName());
    }

    private class ChapterDataInit extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... strings) {
            return "haskjdhaskjdhasjkdhjaskhdkjashdjkashdjkashdjkashdkjas";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }
}

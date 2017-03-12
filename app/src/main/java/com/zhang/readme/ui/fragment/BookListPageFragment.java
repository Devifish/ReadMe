package com.zhang.readme.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zhang.readme.R;
import com.zhang.readme.dao.BooksDao;
import com.zhang.readme.model.BookInfo;
import com.zhang.readme.ui.DetailActivity;
import com.zhang.readme.ui.adapter.BookListRecyclerViewAdapter;
import com.zhang.readme.ui.fragment.base.MainPageLayoutBase;

import java.util.List;

/**
 * Created by zhang on 2017/2/22.
 *
 * @author zhang
 */

public class BookListPageFragment extends MainPageLayoutBase {

    private final static String title = "书架";
    private SwipeRefreshLayout swipeRefreshLayout;
    private BooksDao books;
    private List<BookInfo> list;

    public BookListPageFragment() {super.setTitle(title);}
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.page_booklist_main, container, false);
        books = new BooksDao(this.getContext());
        list = books.getBookList();

        //下拉刷新加载
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.page_booklist_swiperefesh);
        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light,
                android.R.color.holo_red_light
        );
        swipeRefreshLayout.setOnRefreshListener(new BookListPageRefreshListener());

        //RecyclerView加载
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.page_booklist_recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 3));
        BookListRecyclerViewAdapter adapter = new BookListRecyclerViewAdapter(this.getContext(), list);
        adapter.setOnItemClickListener(new RecyclerViewItemClickListener());
        recyclerView.setAdapter(adapter);
        return view;
    }

    /**
     * 下拉刷新监听器
     * 实现书架page页下拉刷新及同步逻辑
     */
    private class BookListPageRefreshListener implements SwipeRefreshLayout.OnRefreshListener {

        @Override
        public void onRefresh() {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    swipeRefreshLayout.setRefreshing(false);
                    Toast.makeText(getContext(), "更新成功", Toast.LENGTH_SHORT).show();
                }
            }, 2000);
        }
    }

    /**
     * RecyclerView Item 点击事件
     * 实现书架item 项目逻辑
     */
    private class RecyclerViewItemClickListener implements BookListRecyclerViewAdapter.OnRecyclerViewItemClickListener {

        @Override
        public void onClick(View v, BookInfo info) {
            Intent intent = new Intent(getContext(), DetailActivity.class);
            intent.putExtra("book_info", info);
            startActivity(intent);
        }

        @Override
        public boolean onLongClick(View v, BookInfo info) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle(info.getTitle());
            builder.setItems(new String[] {"置顶","从书架移除","删除"}, null);
            builder.show();
            return false;
        }
    }
}

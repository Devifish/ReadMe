package com.zhang.readme.view.fragment;

import android.content.DialogInterface;
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
import com.zhang.readme.dao.BookListDao;
import com.zhang.readme.entity.BookList;
import com.zhang.readme.entity.Book;
import com.zhang.readme.view.DetailActivity;
import com.zhang.readme.view.adapter.BookListRecyclerViewAdapter;
import com.zhang.readme.view.fragment.base.MainPageFragmentBase;

/**
 * Created by zhang on 2017/2/22.
 *
 * @author zhang
 */

public class BookListPageFragment extends MainPageFragmentBase {

    private final static String title = "书架";

    private SwipeRefreshLayout swipeRefreshLayout;
    private BookListRecyclerViewAdapter recyclerViewAdapter;
    private BookListDao bookDao;
    private BookList bookList;

    public BookListPageFragment() {super.setTitle(title);}
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.page_booklist_main, container, false);
        bookDao = new BookListDao(this.getContext());
        bookList = bookDao.getBookList();
        bookDao.close();

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
        recyclerViewAdapter = new BookListRecyclerViewAdapter(this.getContext(), bookList);
        recyclerViewAdapter.setOnItemClickListener(new RecyclerViewItemClickListener());
        recyclerView.setAdapter(recyclerViewAdapter);
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
     * RecyclerView Item 点击事件（点击/长按）
     * 实现书架item 项目逻辑
     */
    private class RecyclerViewItemClickListener implements BookListRecyclerViewAdapter.OnRecyclerViewItemClickListener {

        @Override
        public void onClick(View v, Book book, int position) {
            Intent intent = new Intent(getContext(), DetailActivity.class);
            intent.putExtra("book_info", book);
            startActivity(intent);
        }

        @Override
        public void onLongClick(View v, Book info, final int position) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle(info.getTitle());
            builder.setItems(new String[]{"置顶", "缓存到本地", "移出书架"}, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case 0: //置顶
                            break;
                        case 1: //缓存到本地
                            break;
                        case 2: //移出书架
                            bookList.remove(position);
                            recyclerViewAdapter.notifyItemRemoved(position);
                            break;
                        default: break;
                    }
                }
            });
            builder.show();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bookDao.close();
    }
}

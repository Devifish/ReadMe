package com.zhang.readme.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhang.readme.R;
import com.zhang.readme.ui.fragment.base.MainPageLayoutBase;

/**
 * Created by zhang on 2017/2/22.
 *
 * @author zhang
 */

public class BookClassPageFragment extends MainPageLayoutBase {

    private final static String title = "分类";

    public BookClassPageFragment() {super.setTitle(title);}
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.page_class_main, container, false);
        return view;
    }
}

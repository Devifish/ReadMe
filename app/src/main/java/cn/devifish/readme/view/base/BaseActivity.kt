package cn.devifish.readme.view.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager

/**
 * Created by zhang on 2017/6/3.
 *
 */
abstract class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        window.setFlags(
//                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
//                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
//        )
        if (bindLayout() != 0) setContentView(bindLayout())

        initVar()
        initView()
    }

    @LayoutRes
    protected abstract fun bindLayout(): Int

    protected abstract fun initVar()
    protected abstract fun initView()

}
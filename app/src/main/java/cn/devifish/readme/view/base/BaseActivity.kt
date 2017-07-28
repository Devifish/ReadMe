package cn.devifish.readme.view.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by zhang on 2017/6/3.
 *
 */
abstract class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (bindLayout() != 0) setContentView(bindLayout())

        initVar()
        initView()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    @LayoutRes
    protected abstract fun bindLayout(): Int

    protected abstract fun initVar()
    protected abstract fun initView()

}
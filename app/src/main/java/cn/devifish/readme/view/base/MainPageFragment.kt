package cn.devifish.readme.view.base

import android.support.v4.widget.SwipeRefreshLayout

/**
 * Created by zhang on 2017/6/3.
 * 实现主页内的Fragment标题
 */
abstract class MainPageFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener, BaseViewHolder.OnItemClickListener {

    abstract fun getTitle(): String

}
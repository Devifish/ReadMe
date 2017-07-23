package cn.devifish.readme.view.base

import android.support.v4.widget.SwipeRefreshLayout
import android.view.View
import cn.devifish.readme.R

/**
 * Created by zhang on 2017/6/3.
 * 实现主页内的Fragment标题
 */
abstract class MainPageFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener, BaseViewHolder.OnItemClickListener {

    abstract fun getTitle() : String;

    override fun initView(view: View?) {
        val refresh = view!!.findViewById<SwipeRefreshLayout>(R.id.refresh)
        refresh.setOnRefreshListener(this)
        refresh.setColorSchemeResources(
                android.R.color.holo_blue_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light,
                android.R.color.holo_red_light
        )
    }

}
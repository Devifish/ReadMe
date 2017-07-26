package cn.devifish.readme.view.module.stack

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import cn.devifish.readme.R
import cn.devifish.readme.entity.Stack
import cn.devifish.readme.entity.bean.Gender
import cn.devifish.readme.entity.bean.Major
import cn.devifish.readme.provider.RankProvider
import cn.devifish.readme.view.adapter.StackRecyclerAdapter
import cn.devifish.readme.view.base.MainPageFragment
import kotlinx.android.synthetic.main.page_stack_main.*

/**
 * Created by zhang on 2017/6/3.
 * 书库Fragment
 */
class StackFragment : MainPageFragment() {

    private val title = "书库"

    override fun bindLayout(): Int = R.layout.page_stack_main
    override fun getTitle(): String = this.title

    override fun initVar() {}

    override fun initView(view: View?) {
        super.initView(view)

        rv_stack.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val rankProvider = RankProvider()
        val stackList = arrayListOf<Stack>();
        Major.values().forEach {
            item ->
                stackList.add( Stack(item.value, rankProvider.getBooksByCats(Gender.MALE, "hot", item.value, "", 0, 50)))
        }
        rv_stack.adapter = StackRecyclerAdapter(stackList)
    }

    override fun onRefresh() {
    }

    override fun onItemClick(view: View, position: Int) {
    }

    override fun onItemLongClick(view: View, position: Int) {
    }

}
package cn.devifish.readme.view.module.stack

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import cn.devifish.readme.R
import cn.devifish.readme.entity.Stack
import cn.devifish.readme.entity.bean.Gender
import cn.devifish.readme.entity.bean.Major
import cn.devifish.readme.provider.BookProvider
import cn.devifish.readme.service.RankService
import cn.devifish.readme.view.adapter.StackRecyclerAdapter
import cn.devifish.readme.view.base.MainPageFragment
import kotlinx.android.synthetic.main.page_stack_main.*


/**
 * Created by zhang on 2017/6/3.
 * 书库Fragment
 */
class StackFragment : MainPageFragment() {

    private val title = "书库"
    private val rankService = BookProvider.getInstance().create(RankService::class.java)
    private val stackList = ArrayList<Stack>();
    private val adapter = StackRecyclerAdapter();

    override fun bindLayout(): Int = R.layout.page_stack_main
    override fun getTitle(): String = this.title

    override fun initVar() {}

    override fun initView(view: View?) {
        rv_stack.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rv_stack.adapter = adapter
        Major.values().forEach { item ->
            stackList.add( Stack(item.value, rankService.getBooksByCats(Gender.MALE, "hot", item.value, "", 0, 50)))
        }
        adapter.data = stackList
        adapter.setOnItemClickListener(this)
        adapter.activity = activity
    }

    override fun onRefresh() {
        stackList.clear()
    }

    override fun onItemClick(view: View, position: Int) {
    }

    override fun onItemLongClick(view: View, position: Int) {
    }

}
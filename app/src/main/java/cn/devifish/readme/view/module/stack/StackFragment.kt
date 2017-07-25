package cn.devifish.readme.view.module.stack

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import cn.devifish.readme.R
import cn.devifish.readme.entity.Stack
import cn.devifish.readme.provider.RankProvider
import cn.devifish.readme.view.adapter.StackRecyclerAdapter
import cn.devifish.readme.view.base.MainPageFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.page_stack_main.*
import okhttp3.OkHttpClient

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
        RankProvider().getBooksByCats("male", "hot", "玄幻", "东方玄幻", 0, 50)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    t ->
                        val stack = Stack("玄幻", t.books!!);
                        rv_stack.adapter = StackRecyclerAdapter(arrayListOf(stack))

                })
    }

    override fun onRefresh() {
    }

    override fun onItemClick(view: View, position: Int) {
    }

    override fun onItemLongClick(view: View, position: Int) {
    }

}
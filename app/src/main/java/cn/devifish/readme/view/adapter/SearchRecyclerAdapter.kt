package cn.devifish.readme.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.devifish.readme.R
import cn.devifish.readme.entity.Book
import cn.devifish.readme.util.Config
import cn.devifish.readme.view.base.BaseRecyclerAdapter
import cn.devifish.readme.view.base.BaseViewHolder
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.book_item_search.view.*

/**
 * Created by zhang on 2017/8/2.
 * 搜索Rv适配器
 */
class SearchRecyclerAdapter : BaseRecyclerAdapter<Book, SearchRecyclerAdapter.SearchHolder>() {

    override fun onCreateView(group: ViewGroup, viewType: Int): SearchHolder {
        val view = LayoutInflater.from(group.context).inflate(R.layout.book_item_search, group, false)
        return SearchHolder(view)
    }

    override fun onBindView(holder: SearchHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }


    inner class SearchHolder(itemView: View) : BaseViewHolder<Book>(itemView) {

        override fun bind(m: Book) {
            itemView.title.text = m.title
            itemView.author.text = m.author
            itemView.intro.text = m.shortIntro
            Glide.with(itemView.context)
                    .load(Config.IMG_BASE_URL + m.cover)
                    .into(itemView.book_image);
        }

    }

}
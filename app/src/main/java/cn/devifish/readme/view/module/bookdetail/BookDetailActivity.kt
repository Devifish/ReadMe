package cn.devifish.readme.view.module.bookdetail

import cn.devifish.readme.R
import cn.devifish.readme.entity.Book
import cn.devifish.readme.util.Config
import cn.devifish.readme.view.base.BaseActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_book_detail.*

class BookDetailActivity : BaseActivity() {

    private var book : Book? = null

    override fun bindLayout(): Int = R.layout.activity_book_detail

    override fun initVar() {
        book = intent.getParcelableExtra("book")
    }

    override fun initView() {
        supportActionBar!!.title = book!!.title
        Glide.with(this)
                .load(Config.IMG_BASE_URL + book!!.cover)
                .into(book_image)
    }
}

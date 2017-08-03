package cn.devifish.readme.view.module.bookdetail

import cn.devifish.readme.R
import cn.devifish.readme.entity.Book
import cn.devifish.readme.util.BlurTransformation
import cn.devifish.readme.util.Config
import cn.devifish.readme.view.base.BaseActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_book_detail.*

class BookDetailActivity : BaseActivity() {

    private var book : Book? = null

    override fun bindLayout(): Int = R.layout.activity_book_detail

    override fun initVar() {
        book = intent.getParcelableExtra("book")
    }

    override fun initView() {
        setSupportActionBar(toolbar)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        book_title.text = book!!.title
        book_author.text = book!!.author

        Glide.with(this)
                .load(Config.IMG_BASE_URL + book!!.cover)
                .into(book_image)

        Glide.with(this)
                .load(Config.IMG_BASE_URL + book!!.cover)
                .apply(RequestOptions.bitmapTransform(BlurTransformation(this, 25f)))
                .into(image)

    }
}

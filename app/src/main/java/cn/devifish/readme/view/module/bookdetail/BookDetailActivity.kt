package cn.devifish.readme.view.module.bookdetail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import cn.devifish.readme.R
import cn.devifish.readme.entity.Book
import cn.devifish.readme.view.base.BaseActivity

class BookDetailActivity : BaseActivity() {

    private var book : Book? = null

    override fun bindLayout(): Int = R.layout.activity_book_detail

    override fun initVar() {
        book = intent.getParcelableExtra("book")
    }

    override fun initView() {
        supportActionBar!!.title = book!!.title
    }
}

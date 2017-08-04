package cn.devifish.readme.view.module.bookdetail

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.Menu
import android.view.MenuItem
import cn.devifish.readme.R
import cn.devifish.readme.entity.Book
import cn.devifish.readme.util.Config
import cn.devifish.readme.util.GlideRequestListener
import cn.devifish.readme.util.RSBlurUtil
import cn.devifish.readme.view.base.BaseActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.activity_book_detail.*

class BookDetailActivity : BaseActivity() {

    private var book : Book? = null

    override fun bindLayout(): Int = R.layout.activity_book_detail

    override fun initVar() {
        book = intent.getParcelableExtra("book")
    }

    override fun initView() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        search_bar.z = Float.MAX_VALUE

        //内容加载
        book_title.text = book!!.title
        book_author.text = book!!.author
        Glide.with(this).load(Config.IMG_BASE_URL + book!!.cover)
                .listener(object : GlideRequestListener() {
                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?,
                                                 dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        if (resource == null) return false
                        val bitmap = (resource as BitmapDrawable).bitmap
                        image.setImageBitmap(RSBlurUtil.blur(this@BookDetailActivity, bitmap, 20f))
                        return super.onResourceReady(resource, model, target, dataSource, isFirstResource)
                    }
                }).into(book_image)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> this.finishAfterTransition()
            R.id.menu_search -> {search_bar.open(true)}
            R.id.menu_del -> {}
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}

package cn.devifish.readme.view.module.bookdetail

import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.transition.Transition
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewAnimationUtils
import cn.devifish.readme.R
import cn.devifish.readme.entity.Book
import cn.devifish.readme.util.Config
import cn.devifish.readme.util.GlideRequestListenerHelp
import cn.devifish.readme.util.RSBlurUtil
import cn.devifish.readme.util.TransitionListenerHelp
import cn.devifish.readme.view.adapter.BookDetailRecyclerAdapter
import cn.devifish.readme.view.base.BaseActivity
import cn.devifish.readme.view.module.read.ReadActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.activity_book_detail.*
import kotlinx.android.synthetic.main.header_book_detail.*

class BookDetailActivity : BaseActivity(), View.OnClickListener {

    companion object {
        private val OPEN_ACTIVITY = 1
        private val EXIT_ACTIVITY = 2
    }

    private var book: Book? = null
    private var state: Int = OPEN_ACTIVITY
    private var bookInfoPaddingRight: Int = 0

    override fun bindLayout(): Int = R.layout.activity_book_detail

    override fun initVar() {
        book = intent.getParcelableExtra("book")
    }

    override fun initView() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        search_bar.z = Float.MAX_VALUE
        rv_book_detail.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_book_detail.adapter = BookDetailRecyclerAdapter(book!!)

        appBarLayout.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (book_info != null) {
                if (bookInfoPaddingRight == 0) bookInfoPaddingRight = book_info.paddingRight
                book_info.setPadding(0, 0, bookInfoPaddingRight - (-bookInfoPaddingRight * verticalOffset / (appBarLayout.height - supportActionBar!!.height)), 0)
            }
        }

        fab.setOnClickListener(this)

        //内容加载
        book_title.text = book!!.title
        book_author.text = book!!.author
        Glide.with(this).load(Config.IMG_BASE_URL + book!!.cover)
                .listener(object : GlideRequestListenerHelp() {
                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?,
                                                 dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        if (resource == null) return false
                        val bitmap = (resource as BitmapDrawable).bitmap
                        //对背景进行高斯模糊
                        header_image.setImageBitmap(RSBlurUtil.blur(this@BookDetailActivity, bitmap, 20f))
                        header_image.visibility = View.INVISIBLE
                        return super.onResourceReady(resource, model, target, dataSource, isFirstResource)
                    }
                }).into(book_image)
        window.sharedElementEnterTransition.addListener(object : TransitionListenerHelp() {
            override fun onTransitionStart(p0: Transition?) {
                super.onTransitionStart(p0)
                if (state == EXIT_ACTIVITY) {
                    fab.visibility = View.GONE
                }
            }

            override fun onTransitionEnd(p0: Transition?) {
                if (state == OPEN_ACTIVITY) {
                    header_image.visibility = View.VISIBLE
                    ViewAnimationUtils.createCircularReveal(
                            header_image,
                            book_image.left + book_image.width / 2,
                            book_image.top + book_image.height / 2,
                            128f,
                            header_image.width.toFloat()
                    ).setDuration(1000).start()

                    state = EXIT_ACTIVITY
                }
            }
        })
    }

    override fun onClick(p0: View) {
        when (p0.id) {
            R.id.fab -> {
                /*Snackbar.make(p0, R.string.test, Snackbar.LENGTH_SHORT)
                        .setAction(R.string.close, { view ->

                        }).show()*/
                startActivity(Intent(this, ReadActivity::class.java))
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> this.finishAfterTransition()
            R.id.menu_search -> search_bar.open(true)
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

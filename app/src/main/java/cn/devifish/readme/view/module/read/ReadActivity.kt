package cn.devifish.readme.view.module.read

import ScreenUtils
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import cn.devifish.readme.R
import cn.devifish.readme.view.adapter.ReadViewPageAdapter
import cn.devifish.readme.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_read.*

class ReadActivity : BaseActivity() {

    private var mVisible: Boolean = false
    private var mScreenHeight =  0
    private var mScreenWidth = 0

    private var startX = 0f
    private var startY = 0f

    override fun bindLayout(): Int = R.layout.activity_read

    override fun initVar() {
        mScreenHeight = ScreenUtils.getScreenHeight(this)
        mScreenWidth = ScreenUtils.getScreenWidth(this)

        fullscreen_content.setOnTouchListener { view, event ->
            view.onTouchEvent(event)

            // 防止滑动过程中唤醒菜单
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    startX = event.x
                    startY = event.y
                }
                MotionEvent.ACTION_UP -> {
                    // 点击屏幕中间唤醒菜单
                    if (event.y > mScreenHeight * 2 / 5 && event.y < mScreenHeight * 3 / 5
                            && event.x > mScreenWidth * 2 / 7 && event.x < mScreenWidth * 5 / 7
                            && event.x == startX && event.y == startY) {
                        mVisible = !mVisible
                        setActionBarAndMenuVisibility(mVisible)
                    }
                }
            }
            true
        }
    }

    override fun initView() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // 隐藏ActionBar和底部菜单
        setActionBarAndMenuVisibility(false)

        fullscreen_content.adapter = ReadViewPageAdapter(this.supportFragmentManager)
    }

    private fun setActionBarAndMenuVisibility(visible: Boolean) {
        if (visible) {
            fullscreen_content.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

            supportActionBar?.show()
            fullscreen_content_controls.visibility = View.VISIBLE
        }else {
            fullscreen_content.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LOW_PROFILE or
                            View.SYSTEM_UI_FLAG_FULLSCREEN or
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION

            supportActionBar?.hide()
            fullscreen_content_controls.visibility = View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_read, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> this.finish()
            R.id.menu_share -> {}
        }

        return super.onOptionsItemSelected(item)
    }

}

package cn.devifish.readme.view.module.main

import android.content.Intent
import android.net.Uri
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import cn.devifish.readme.R
import cn.devifish.readme.entity.data.RankFemaleData
import cn.devifish.readme.provider.RankProvider
import cn.devifish.readme.provider.SearchProvider
import cn.devifish.readme.util.Config
import cn.devifish.readme.view.adapter.MainViewPageAdapter
import cn.devifish.readme.view.base.BaseActivity
import cn.devifish.readme.view.module.login.LoginActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import okhttp3.OkHttpClient

/**
 * Created by zhang on 2017/6/3.
 * 主页Activity
 */
class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun bindLayout(): Int = R.layout.activity_main

    override fun initVar() {}

    override fun initView() {
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        view_pager.adapter = MainViewPageAdapter(this.supportFragmentManager)
        tab_layout.setupWithViewPager(view_pager)

        nav_view.getHeaderView(0).setOnClickListener { startActivity(Intent(this, LoginActivity::class.java)) }
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null)
                    .show()
        }

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_search -> {}
            R.id.menu_exit -> this.finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_bookshelf -> view_pager.currentItem = Config.Page.BOOKSHELF_PAGE
            R.id.nav_stack -> view_pager.currentItem = Config.Page.STACK_PAGE
            R.id.nav_community -> view_pager.currentItem = Config.Page.COMMUNITY_PAGE
            R.id.nav_setting -> {}
            R.id.nav_send -> {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://ds.alipay.com/?from=mobilecodec&scheme=alipays%3A%2F%2Fplatformapi%2Fstartapp%3FsaId%3D10000007%26clientVersion%3D3.7.0.0718%26qrcode%3Dhttps%253A%252F%252Fqr.alipay.com%252FFKX01040EFSY2JLIHKMT8B%253F_s%253Dweb-other")))
            }
            R.id.nav_info -> {}
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}

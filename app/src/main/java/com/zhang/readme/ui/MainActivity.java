package com.zhang.readme.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.zhang.readme.R;
import com.zhang.readme.ui.adapter.MainViewPageAdapter;

/**
 * Created by zhang on 2017/1/16.
 *
 * MainActivity 实现逻辑
 *
 * @author zhang
 */

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /* 绑定Support库的Toolbar */
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /* FloatActionButton功能逻辑 */
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "滑动清除该信息", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null)
                        .show();
            }
        });

        /* Navigation汉堡菜单加载 */
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /* TabLayout与ViewPage加载 */
        MainViewPageAdapter mainViewPageAdapter = new MainViewPageAdapter(this.getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.main_viewpage);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.main_tab);

        viewPager.setAdapter(mainViewPageAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                //当ViewPage 切换时给FloatingActionButton 添加动画
                switch (position) {
                    case 0:
                        fab.show();
                        fab.setImageResource(R.drawable.ic_add);
                        break;
                    case 1: fab.hide();
                        break;
                    case 2:
                        fab.show();
                        fab.setImageResource(R.drawable.ic_edit);
                        break;
                    default: break;
                }
            }
        });
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.menu_search:
                startActivity(new Intent(this, SearchActivity.class));
                break;
            case R.id.menu_open:
                break;
            case R.id.menu_test:
                break;
            case R.id.menu_setting:
                break;
            case R.id.menu_exit:
                System.exit(0);
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_booklist:
                viewPager.setCurrentItem(0);
                break;
            case R.id.nav_bookclass:
                viewPager.setCurrentItem(1);
                break;
            case R.id.nav_community:
                viewPager.setCurrentItem(2);
                break;
            case R.id.nav_setting:
                break;
            case R.id.nav_send:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://ds.alipay.com/?from=mobilecodec&scheme=alipays%3A%2F%2Fplatformapi%2Fstartapp%3FsaId%3D10000007%26clientVersion%3D3.7.0.0718%26qrcode%3Dhttps%253A%252F%252Fqr.alipay.com%252FFKX01040EFSY2JLIHKMT8B%253F_s%253Dweb-other")));

                break;
            case R.id.nav_info:
                startActivity(new Intent(this, InfoActivity.class));
                break;
            default: break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

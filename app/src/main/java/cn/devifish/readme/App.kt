package cn.devifish.readme

import android.app.Application
import com.scwang.smartrefresh.header.MaterialHeader
import com.scwang.smartrefresh.header.WaveSwipeHeader
import com.scwang.smartrefresh.layout.constant.SpinnerStyle
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater
import com.scwang.smartrefresh.layout.footer.BallPulseFooter


/**
 * Created by zhang on 2017/6/4.
 *
 */
class App : Application() {

    companion object {
        init {
            SmartRefreshLayout.setDefaultRefreshHeaderCreater(DefaultRefreshHeaderCreater { context, layout ->
                MaterialHeader(context)
            })

            SmartRefreshLayout.setDefaultRefreshFooterCreater(DefaultRefreshFooterCreater { context, layout ->
                BallPulseFooter(context).setSpinnerStyle(SpinnerStyle.Scale);
            })
        }
    }

    override fun onCreate() {
        super.onCreate()
    }

}
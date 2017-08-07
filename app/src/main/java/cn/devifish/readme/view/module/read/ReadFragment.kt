package cn.devifish.readme.view.module.read

import android.os.Bundle
import android.util.Log
import android.view.View
import cn.devifish.readme.R

import cn.devifish.readme.view.base.BaseFragment

/**
 * Created by zhang on 2017/8/7.

 */

class ReadFragment : BaseFragment() {

    companion object {
        fun newInstance(): ReadFragment {
            val args = Bundle()
            val fragment = ReadFragment()

            fragment.arguments = args
            return fragment
        }
    }

    override fun bindLayout(): Int = R.layout.content_read

    override fun initVar() {

    }

    override fun initView(view: View) {

    }

}

package cn.devifish.readme.util

import android.transition.Transition

/**
 * Created by zhang on 2017/8/4.
 * 动画监听事件帮助类
 */
open class TransitionListenerHelp : Transition.TransitionListener {

    override fun onTransitionEnd(p0: Transition?) {}

    override fun onTransitionResume(p0: Transition?) {}

    override fun onTransitionPause(p0: Transition?) {}

    override fun onTransitionCancel(p0: Transition?) {}

    override fun onTransitionStart(p0: Transition?) {}
}
package cn.devifish.readme.entity

import java.util.*

/**
 * Created by zhang on 2017/7/22.
 * 书籍榜单
 */
data class Rank(
        var _id: String? = null,
        var updated: Date? = null,
        var title: String? = null,
        var tag: String? = null,
        var cover: String? = null,
        var icon: String? = null,
        var monthRank: String? = null,
        var totalRank: String? = null,
        var shortTitle: String? = null
)
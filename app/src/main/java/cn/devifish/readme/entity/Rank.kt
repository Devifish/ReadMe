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
        var shortTitle: String? = null,
        var created: Date? = null,
        var isSub: Boolean = false,
        var collapse: Boolean = false,
        var new: Boolean = false,
        var gender: String? = null,
        var priority: Int = 0,
        var books: List<Book>? = null,
        var total: Int = 0
)
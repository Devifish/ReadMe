package cn.devifish.readme.entity

/**
 * Created by zhang on 2017/7/22.
 * 书籍榜单集合种类
 */
data class RankFemale (
        var _id: String? = null,
        var title: String? = null,
        var cover: String? = null,
        var collapse: Boolean = false,
        var monthRank: String? = null,
        var totalRank: String? = null,
        var shortTitle: String? = null
)
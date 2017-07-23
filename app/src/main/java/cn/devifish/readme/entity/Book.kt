package cn.devifish.readme.entity

/**
 * Created by zhang on 2017/6/6.
 *
 */
data class Book(
        var _id: String? = null,
        var title: String? = null,
        var author: String? = null,
        var shortIntro: String? = null,
        var cover: String? = null,
        var site: String? = null,
        var majorCate: String? = null,
        var minorCate: String? = null,
        var banned: Int = 0,
        var latelyFollower: Int = 0,
        var followerCount: Int = 0,
        var retentionRatio: Float = 0.0f,
        var lastChapter: String? = null
)
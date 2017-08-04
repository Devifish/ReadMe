package cn.devifish.readme.entity

import java.util.*

/**
 * Created by zhang on 2017/8/4.
 *
 */
data class BookDetail(
        var _id: String? = null,
        var title: String? = null,
        var author: String? = null,
        var longIntro: String? = null,
        var cover: String? = null,
        var majorCate: String? = null,
        var minorCate: String? = null,
        var creater: String? = null,
        var sizetype: Int = 0,
        var superscript: String? = null,
        var currency: Int = 0,
        var contentType: String? = null,
        var _le: Boolean = false,
        var allowMonthly: Boolean = false,
        var allowVoucher: Boolean = false,
        var allowBeanVoucher: Boolean = false,
        var hasCp: Boolean = false,
        var postCount: Int = 0,
        var latelyFollower: Int = 0,
        var followerCount: Int = 0,
        var wordCount: Int = 0,
        var serializeWordCount: Int = 0,
        var retentionRatio: Float = 0.0f,
        var updated: Date? = null,
        var isSerial: Boolean = false,
        var chaptersCount: Int = 0,
        var lastChapter: String? = null
)
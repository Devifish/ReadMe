package cn.devifish.readme.entity

/**
 * Created by zhang on 2017/7/22.
 *
 */
data class Books (
        var total: Int = 0,
        var books: List<Book>? = null,
        var ok: Boolean = false
)
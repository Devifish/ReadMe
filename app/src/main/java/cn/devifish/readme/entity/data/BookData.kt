package cn.devifish.readme.entity.data

import cn.devifish.readme.entity.Book

/**
 * Created by zhang on 2017/7/22.
 *
 */
data class BookData(
        var total: Int = 0,
        var books: List<Book>? = null,
        var ok: Boolean = false
)
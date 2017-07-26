package cn.devifish.readme.entity

import cn.devifish.readme.entity.data.BookData
import io.reactivex.Observable

/**
 * Created by zhang on 2017/6/9.
 *
 */
data class Stack(
        var name: String,
        var data: Observable<BookData>,
        var list: List<Book>? = null
)
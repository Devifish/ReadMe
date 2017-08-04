package cn.devifish.readme.service

import cn.devifish.readme.entity.BookDetail
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * Created by zhang on 2017/7/12.
 *
 */
interface BookService {

    /**
     * 书籍详细信息
     * url: http://api.zhuishushenqi.com/book/5816b415b06d1d32157790b1
     * @param bookId 书Id
     */
    @GET("/book/{bookId}")
    fun getBookDetail(@Path("bookId") bookId: String): Observable<BookDetail>

}
package cn.devifish.readme.service

import cn.devifish.readme.entity.BookDetail
import cn.devifish.readme.entity.data.BookData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


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

    /**
     * 解析小说章节内容
     * url: http://api.zhuishushenqi.com/chapter/
     * @param bookId 书Id
     */
    @GET("/chapter/{bookId}")
    fun getChapterContext(@Path("bookId") bookId: String): Observable<String>

    /**
     * 相关书籍推荐
     * url: http://api.zhuishushenqi.com/book/5816b415b06d1d32157790b1/recommend
     * @param bookId 书Id
     */
    @GET("/book/{bookId}/recommend")
    fun getCommendBook(@Path("bookId") bookId: String): Observable<BookData>

    /**
     * 相关书单推荐
     * url: http://api.zhuishushenqi.com/book-list/5816b415b06d1d32157790b1/recommend
     * @param bookId 书Id
     */
    @GET("/book-list/{bookId}/recommend")
    fun getCommendBookList(@Path("bookId") bookId: String, @Query("limit") limit: String): Observable<String>

    /**
     * 相关热门书评
     * url: http://api.zhuishushenqi.com/post/review/best-by-book?book=5816b415b06d1d32157790b1
     * @param bookId 书Id
     */
    @GET("/post/review/best-by-book")
    fun getHotReview(@Query("book") bookId: String): Observable<String>


}
package cn.devifish.readme.service

import cn.devifish.readme.entity.data.BookData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by zhang on 2017/7/12.
 * 追书神器书籍查询Api
 */
interface SearchService {

    /**
     * 书籍查询
     * url: http://api.zhuishushenqi.com/book/fuzzy-search?query=遮天
     * @param query 书名
     */
    @GET("/book/fuzzy-search")
    fun searchBooks(@Query("query") query: String): Observable<BookData>

    /**
     * 通过书名查询书籍
     * url: http://api.zhuishushenqi.com/book/accurate-search?title=遮天
     * @param title 书名
     */
    @GET("/book/accurate-search")
    fun searchBooksByTitle(@Query("title") title: String): Observable<BookData>

    /**
     * 通过作者查询书名
     * url: http://api.zhuishushenqi.com/book/accurate-search?author=辰东
     * @param author 作者
     */
    @GET("/book/accurate-search")
    fun searchBooksByAuthor(@Query("author") author: String): Observable<BookData>

    /**
     * 关键字自动补全
     * url: http://api.zhuishushenqi.com/book/auto-complete?query=天
     * @param query
     * @return
     */
    @GET("/book/auto-complete")
    fun autoComplete(@Query("query") query: String): Observable<String>

}
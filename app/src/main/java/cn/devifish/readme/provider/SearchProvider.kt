package cn.devifish.readme.provider

import cn.devifish.readme.entity.data.BookData
import cn.devifish.readme.service.SearchService
import io.reactivex.Observable
import okhttp3.OkHttpClient

/**
 * Created by zhang on 2017/7/22.
 * 小说搜索内容提供
 */
class SearchProvider : BaseProvider(), SearchService {

    private val searchService : SearchService = super.retrofit.create(SearchService::class.java)

    override fun searchBooks(query: String): Observable<BookData> = searchService.searchBooks(query)

    override fun searchBooksByTitle(title: String): Observable<BookData> = searchService.searchBooksByTitle(title)

    override fun searchBooksByAuthor(author: String): Observable<BookData> = searchService.searchBooksByAuthor(author)

    override fun autoComplete(query: String): Observable<String> = searchService.autoComplete(query)

}
package cn.devifish.readme.provider

import cn.devifish.readme.entity.Book
import cn.devifish.readme.entity.Books
import cn.devifish.readme.service.SearchService
import io.reactivex.Observable
import okhttp3.OkHttpClient

/**
 * Created by zhang on 2017/7/22.
 *
 */
class SearchProvider(client: OkHttpClient) : BaseProvider(client), SearchService {

    private val searchService : SearchService = super.retrofit.create(SearchService::class.java)

    override fun searchBooks(query: String): Observable<Books> = searchService.searchBooks(query)

    override fun searchBooksByTitle(title: String): Observable<Books> = searchService.searchBooksByTitle(title)

    override fun searchBooksByAuthor(author: String): Observable<Books> = searchService.searchBooksByAuthor(author)

}
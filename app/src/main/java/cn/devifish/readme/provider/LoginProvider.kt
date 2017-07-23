package cn.devifish.readme.provider

import cn.devifish.readme.entity.Book
import cn.devifish.readme.service.LoginService
import cn.devifish.readme.service.SearchService
import io.reactivex.Observable
import okhttp3.OkHttpClient

/**
 * Created by zhang on 2017/6/12.
 *
 */
class LoginProvider(client: OkHttpClient) : BaseProvider(client) {

    private val loginService: LoginService = super.retrofit.create(LoginService::class.java)


}
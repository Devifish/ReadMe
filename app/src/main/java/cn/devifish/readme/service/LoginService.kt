package cn.devifish.readme.service

import cn.devifish.readme.entity.User
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created by zhang on 2017/6/11.
 *
 */
interface LoginService {

    @POST()
    fun login(name: String, password: String)

    @GET(value = "/ffff")
    fun regedit(user: User)

}
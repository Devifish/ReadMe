package cn.devifish.readme.service

import cn.devifish.readme.entity.data.BookData
import cn.devifish.readme.entity.data.RankData
import cn.devifish.readme.entity.data.RankFemaleData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/**
 * Created by zhang on 2017/7/22.
 * 追书神器榜单Api
 */
interface RankService {

    /**
     * 获取所有排行榜
     * url: http://api.zhuishushenqi.com/ranking/gender
     */
    @GET("/ranking/gender")
    fun getRanking(): Observable<RankFemaleData>

    /**
     * 获取单一排行榜
     * 周榜：rankingId->_id
     * 月榜：rankingId->monthRank
     * 总榜：rankingId->totalRank
     * url: http://api.zhuishushenqi.com/ranking/54d42d92321052167dfb75e3
     * @param rankingId 榜单ID
     */
    @GET("/ranking/{rankingId}")
    fun getRanking(@Path("rankingId") rankingId: String): Observable<RankData>

    /**
     * 按分类获取书籍列表
     * url: http://api.zhuishushenqi.com/book/by-categories?major=%E7%8E%84%E5%B9%BB&limit=15&type=hot&gender=male&start=0
     * @param gender male、female
     * @param type   hot(热门)、new(新书)、reputation(好评)、over(完结)
     * @param major  玄幻
     * @param minor  东方玄幻、异界大陆、异界争霸、远古神话
     * @param limit  50
     * @return
     */
    @GET("/book/by-categories")
    fun getBooksByCats(@Query("gender") gender: String, @Query("type") type: String, @Query("major") major: String, @Query("minor") minor: String, @Query("start") start: Int, @Query("limit") limit: Int): Observable<BookData>


}
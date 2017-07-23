package cn.devifish.readme.service

import cn.devifish.readme.entity.Rank
import cn.devifish.readme.entity.RankFemales
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path


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
    fun getRanking(): Observable<RankFemales>

    /**
     * 获取单一排行榜
     * 周榜：rankingId->_id
     * 月榜：rankingId->monthRank
     * 总榜：rankingId->totalRank
     * url: http://api.zhuishushenqi.com/ranking/54d42d92321052167dfb75e3
     * @param rankingId 榜单ID
     */
    @GET("/ranking/{rankingId}")
    fun getRanking(@Path("rankingId") rankingId: String): Observable<Rank>

}
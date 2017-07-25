package cn.devifish.readme.provider

import cn.devifish.readme.entity.data.BookData
import cn.devifish.readme.entity.data.RankData
import cn.devifish.readme.entity.data.RankFemaleData
import cn.devifish.readme.service.RankService
import io.reactivex.Observable
import okhttp3.OkHttpClient

/**
 * Created by zhang on 2017/7/23.
 * 小说榜单内容提供
 */
class RankProvider : BaseProvider(), RankService {

    private val ranService: RankService = super.retrofit.create(RankService::class.java)

    override fun getRanking(): Observable<RankFemaleData> = ranService.getRanking()

    override fun getRanking(rankingId: String): Observable<RankData> = ranService.getRanking(rankingId)

    override fun getBooksByCats(gender: String, type: String, major: String, minor: String, start: Int, limit: Int): Observable<BookData> {
        return ranService.getBooksByCats(gender, type, major, minor, start, limit)
    }

}
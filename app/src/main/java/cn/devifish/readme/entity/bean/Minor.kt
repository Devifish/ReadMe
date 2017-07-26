package cn.devifish.readme.entity.bean

/**
 * Created by zhang on 2017/7/25.
 * 书籍次要类型
 */
enum class Minor(val value: String) {

    XH_DFXH("东方玄幻"), XH_YJDL("异界大陆"), XH_YJZB("异界争霸"), XH_YGSH("远古神话");

    override fun toString(): String = value

}
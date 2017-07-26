package cn.devifish.readme.entity.bean

/**
 * Created by zhang on 2017/7/25.
 * 书籍主要类型
 */
enum class Major(val value: String) {

    XH("玄幻"), QH("奇幻"), WX("武侠"), XX("仙侠"),
    DS("都市"), ZC("职场"), LS("历史"), JS("军事"),
    YX("游戏"), JJ("竞技"), KH("科幻"), LY("灵异"),
    TR("同人"), QXS("轻小说");

    override fun toString(): String = value

}
package cn.devifish.readme.entity.bean

/**
 * Created by zhang on 2017/7/25.
 * 性别类型
 */
enum class Gender(val value: String) {

    MALE("male"), FEMALE("female");

    override fun toString(): String = value

}
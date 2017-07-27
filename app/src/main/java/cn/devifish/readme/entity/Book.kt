package cn.devifish.readme.entity

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by zhang on 2017/6/6.
 *
 */
data class Book(
        var _id: String? = null,
        var title: String? = null,
        var author: String? = null,
        var shortIntro: String? = null,
        var cover: String? = null,
        var site: String? = null,
        var majorCate: String? = null,
        var minorCate: String? = null,
        var banned: Int = 0,
        var latelyFollower: Int = 0,
        var followerCount: Int = 0,
        var retentionRatio: Float = 0.0f,
        var lastChapter: String? = null
) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readFloat(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
        parcel.writeString(title)
        parcel.writeString(author)
        parcel.writeString(shortIntro)
        parcel.writeString(cover)
        parcel.writeString(site)
        parcel.writeString(majorCate)
        parcel.writeString(minorCate)
        parcel.writeInt(banned)
        parcel.writeInt(latelyFollower)
        parcel.writeInt(followerCount)
        parcel.writeFloat(retentionRatio)
        parcel.writeString(lastChapter)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Book> {
        override fun createFromParcel(parcel: Parcel): Book {
            return Book(parcel)
        }

        override fun newArray(size: Int): Array<Book?> {
            return arrayOfNulls(size)
        }
    }

}
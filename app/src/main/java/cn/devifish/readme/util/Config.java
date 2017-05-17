package cn.devifish.readme.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by zhang on 2017/1/16.
 *
 * 资源参数类  管理各项默认设置参数
 *
 * @author zhang
 */

public interface Config {

    /* 小说资源网站 */
    public static final String MAIN_RES = "http://www.52biquge.com/";

    /* 服务器相关参数 */
    public static interface Server {
        public static final String IP_ADDRESS = "127.0.0.1";
        public static final int PORT = 10086;
    }

    /* 数据库相关参数 */
    public static interface Database {
        public final static String DB_NAME = "readme.db";

        //书架表
        public final static String SQL_BOOKS = "CREATE TABLE books (" +
                "  _id             INTEGER PRIMARY KEY NOT NULL,  " +
                "  title           VARCHAR(50)         NOT NULL,  " +
                "  author          VARCHAR(10),                   " +
                "  book_path       TEXT                NOT NULL,  " +
                "  image_path      TEXT                           " +
                ")";

        //书签表
        public final static String SQL_BOOKMARK = "CREATE TABLE bookmark (" +
                "  _id             INTEGER PRIMARY KEY NOT NULL,  " +
                "  book_id         INTEGER             NOT NULL,  " +
                "  name            VARCHAR(50),                   " +
                "  book_index      INTEGER DEFAULT 0,             " +
                "  mark_class      VARCHAR(10) CHECK(mark_class='auto' OR mark_class='user') " +
                ")";

        //用户表
        public final static String SQL_USER = "CREATE TABLE user (" +
                "  _id             INTEGER PRIMARY KEY NOT NULL,  " +
                "  user            VARCHAR(20)         NOT NULL,  " +
                "  cookie          TEXT                NOT NULL,  " +
                "  image_path      TEXT                           " +
                ")";
    }

    /* App信息相关 */
    public static final class AppInfo {

        //版本名
        public static String getVersionName(Context context) {
            PackageInfo info = getPackageInfo(context);
            if (info != null) return info.versionName;
            else return "-1";
        }

        //版本号
        public static int getVersionCode(Context context) {
            PackageInfo info = getPackageInfo(context);
            if (info != null) return info.versionCode;
            else return -1;
        }

        private static PackageInfo getPackageInfo(Context context) {
            try {

                PackageManager pm = context.getPackageManager();
                return pm.getPackageInfo(context.getPackageName(),
                            PackageManager.GET_CONFIGURATIONS);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}

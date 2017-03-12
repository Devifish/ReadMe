package com.zhang.readme.tools;

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

    /** 小说资源网站 */
    public static final String MAIN_RES = "http://www.8dushu.com/";

    /** 服务器相关参数 */
    public static interface Server {
        public static final String IP_ADDRESS = "127.0.0.1";
        public static final int PORT = 10086;
    }

    /** App信息相关 */
    public static class AppInfo {

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

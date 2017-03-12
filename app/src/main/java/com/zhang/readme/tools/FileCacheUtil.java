package com.zhang.readme.tools;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by zhang on 2017/3/10.
 *
 * 文件缓存工具类
 */

public class FileCacheUtil {

    @Nullable
    public static File getFileByURL(Context context, String url) {
        File file = new File(getFileAtCachePath(context, url));
        if (file.exists()) {
            return file;
        }else {
            if (saveFileForCacheDir(url, file.getAbsolutePath())) {
                return file;
            }else {
                return null;
            }
        }
    }

    public static String getFileAtCachePath(Context context, String url) {
        File cache = context.getCacheDir();
        String fileName = url.substring(url.lastIndexOf("/"), url.length());
        return cache.getAbsolutePath() + fileName;
    }

    public static boolean saveFileForCacheDir(String url, String savePath) {
        try {
            URL u = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) u.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            if (connection.getResponseCode() == 200) {
                InputStream is = connection.getInputStream();
                FileOutputStream fos = new FileOutputStream(savePath);

                int length;
                byte[] cache = new byte[4096];
                while ((length = is.read(cache)) != -1) {
                    fos.write(cache, 0, length);
                }
                fos.flush();
                is.close();
                fos.close();

                return true;
            }else return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

package com.zhang.readme.util;

import android.content.Context;

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

    public static File getFileByURL(Context context, String url) {
        if (url != null) {
            File file = getFileAtCachePath(context, url);
            if (file.exists()) {
                return file;
            } else if (saveFileForCacheDir(url, file.getAbsolutePath())) {
                return file;
            }
        }
        return null;
    }

    public static File getFileAtCachePath(Context context, String url) {
        String fileName = url.substring(url.lastIndexOf("/"), url.length());
        return new File(context.getCacheDir(), fileName);
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

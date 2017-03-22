package com.zhang.readme.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.zhang.readme.R;

import java.io.IOException;
import java.io.InputStream;

public class SplashActivity extends Activity {

    SplashHandler splashhandler;
    Handler x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //加载Splah图片
        ImageView image = (ImageView) findViewById(R.id.splash_img);
        try {
            InputStream is = getAssets().open("splash_launcher.png");
            Drawable splash = Drawable.createFromStream(is, "splash");
            image.setImageDrawable(splash);

            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        x = new Handler();
        splashhandler = new SplashHandler();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(x != null && splashhandler != null){
            x.removeCallbacks(splashhandler);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        x.postDelayed(splashhandler, 1000);
    }

    private class SplashHandler implements Runnable{

        public void run() {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }
}

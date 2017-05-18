package cn.devifish.readme.view;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import cn.devifish.readme.R;

import java.io.IOException;
import java.io.InputStream;

public class SplashActivity extends AppCompatActivity {

    SplashHandler mSplashHandler;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //加载Splash图片
        ImageView image = (ImageView) findViewById(R.id.splash_img);
        try {
            InputStream is = getAssets().open("splash_launcher.webp");
            image.setImageDrawable(Drawable.createFromStream(is, "splash"));

            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        handler = new Handler();
        mSplashHandler = new SplashHandler();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(handler != null && mSplashHandler != null){
            handler.removeCallbacks(mSplashHandler);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        handler.postDelayed(mSplashHandler, 1000);
    }

    private class SplashHandler implements Runnable{

        public void run() {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }
}

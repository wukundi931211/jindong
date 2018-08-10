package com.baiwei.tianlong.jindong.mvp.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.baiwei.tianlong.jindong.R;


import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.textView)
    TextView textView;


    private Intent intent;
    private MyHandler handler = new MyHandler();
    private int time = 5;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //初始化
        initView();
    }

    private void initView() {
        intent = new Intent(MainActivity.this,HomeActivity.class);
//        sharedPreferences = getSharedPreferences("Headline",MODE_PRIVATE);
//        editor = sharedPreferences.edit();
//
//        boolean isload = sharedPreferences.getBoolean("isload", false);
//        if (isload){
//            startActivity(intent);
//            finish();
//        }else {
//
//        }
        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i <6 ; i++) {
                    handler.sendEmptyMessage(time);
                    time--;
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (time == 0){
//                        editor.putBoolean("isload",true);
//                        editor.commit();
                        startActivity(intent);
                        finish();
                    }
                }
                super.run();
            }
        }.start();

    }

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            textView.setText(msg.what+"s");
        }
    }
}

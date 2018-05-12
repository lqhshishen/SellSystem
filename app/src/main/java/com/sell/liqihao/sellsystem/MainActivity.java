package com.sell.liqihao.sellsystem;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sell.liqihao.sellsystem.module.Login.ui.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    Activity mContext;
    boolean a;

    Runnable run = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    };
    @BindView(R.id.first_img)
    ImageView firstimage;

    @SuppressLint("HandlerLeak")
    private Handler handle = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    handle.postDelayed(run, 5 * 1000);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Message message = new Message();
        message.what = 1;
        handle.sendMessage(message);
        Glide.with(this)
                .load("http://pic.yupoo.com/lqhshishen/550f248b/a45df015.png")
                .into(firstimage);

//        http://pic.yupoo.com/lqhshishen/e2876434/1f86942c.jpg
    }

    @OnClick(R.id.first_img)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.first_img:
                handle.removeCallbacks(run);
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
        }
    }
}

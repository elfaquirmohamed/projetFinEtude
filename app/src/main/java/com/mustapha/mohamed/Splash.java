package com.mustapha.mohamed;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.mustapha.mohamed.chatbot.ChatActivity;

import androidx.appcompat.app.AppCompatActivity;


public class Splash extends AppCompatActivity {

    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
            Intent intent=new Intent(Splash.this, MainActivity.class);
             //   Intent intent=new Intent(Splash.this, ChatActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);
    }
}


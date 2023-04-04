package com.example.capston_irc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private MyIRCClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // IRC 클라이언트 객체 생성
        ConnectThread connectThread = new ConnectThread("irc.example.com", 6667, "myNickname");
        connectThread.start();
    }
}
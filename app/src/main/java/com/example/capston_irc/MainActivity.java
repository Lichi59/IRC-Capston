package com.example.capston_irc;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.capston_irc.MESSAGE";
    private MyIRCClient client;
    Button chat_btn;
    TextView chat_tv;
    EditText chat_edt;
    String nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 연결
        chat_btn = findViewById(R.id.chat_btn);
        chat_edt = findViewById(R.id.chat_edt);
        chat_tv = findViewById(R.id.chat_tv);

        // 인텐트로 전달된 닉네임 정보
        Intent intent = getIntent();
        nickname = intent.getStringExtra("nickname"); //닉네임 정보는 이름으로

        // IRC 클라이언트 객체 생성
        Configuration.Builder configuration = new Configuration.Builder()
                .setName(nickname)
                .addAutoJoinChannel("#test")
                .setAutoReconnect(true)
                .setCapEnabled(true);

        try {
            client = new MyIRCClient(configuration);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IrcException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 서버에 연결
        new Thread(() -> {
            try {
                client.startBot();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (IrcException e) {
                e.printStackTrace();
            }
        }).start();


        // 버튼 코드
        chat_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(); //밑에 함수 선언
            }
        });
    }

    public void sendMessage() {
        // EditText에서 메시지를 가져옴.
        EditText chat_edt = findViewById(R.id.chat_edt);
        String message = chat_edt.getText().toString();

        // TextView에 메시지를 보여줌.
        String oldText = chat_tv.getText().toString();
        String newText = oldText + "\n" + nickname + ": " + message;
        chat_tv.setText(newText);

        // IRC 서버로 메시지를 보냄.
        String channel = "#test"; // 수정 필요
        client.sendMessage(channel, message);

        // EditText 내용을 지움.
        chat_edt.setText("");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        client.stopBotReconnect();
        client.sendIRC().quitServer();
    }
}

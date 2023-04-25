package com.example.capston_irc;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.pircbotx.Configuration;
import org.pircbotx.exception.IrcException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.capston_irc.MESSAGE";
    private MyIRCClient client;
    Button chat_btn;
    TextView chat_tv;
    EditText chat_edt;
    String nickname;
    String channel = "#test";

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
        nickname = intent.getStringExtra("com.example.capston_irc.nickname"); //닉네임 정보는 이름으로 => 하니까 오류나서 고침

        // IRC 클라이언트 객체 생성
        if (nickname != null) {
            // 예외 처리 로직 추가
            Configuration.Builder configurationBuilder = new Configuration.Builder()
                    .setName(nickname) //받아온 이름으로 닉네임 설정했음 => 왜 오류가 나는거야 짜증 => 얼추 해결
                    .addServer("irc.example.com")
                    .addAutoJoinChannel("#test") //채널 설정
                    .setAutoReconnect(true)
                    .setCapEnabled(true);
            try {
                client = new MyIRCClient(configurationBuilder, channel);
            } catch (IOException | IrcException e) {
                e.printStackTrace();
            }
        }

            // 서버에 연결
            new Thread(() -> { //새로운 스레드 생성
                try {
                    client.startBot(); //여기서 봇을 부르는데, client가 null이라 현재 오류 발생 중 => 일단 해결상태
                } catch (IOException | IrcException e) {
                    e.printStackTrace();
                }
            }).start();

            // 버튼 코드
            chat_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (client != null) {
                        sendMessage(channel);
                    } else {
                        Toast.makeText(MainActivity.this, "서버 연결 중입니다. 잠시 후 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                    }
                 //밑에 함수 선언
                } //버튼을 클릭하면 sendMessage가 실행.
                //현재 막힌 부분은, MyClient가 굳이 없어도 현재 sendMessage를 만들어뒀다는 것.
                //하지만 우리가 원하는 것은 봇을 통한 관리로, 이 부분을 어떻게 처리할 지 고민 중이다.
            });
    }
        public void sendMessage(String channel) {//channel을 매개변수로 추가
            // EditText에서 메시지를 가져옴.
            String message = chat_edt.getText().toString();

            // TextView에 메시지를 보여줌.
            try {
                String oldText = chat_tv.getText().toString();
                String newText = oldText + "\n" + nickname + ": " + message;
                chat_tv.setText(newText);
            } catch (Exception e) {
                Toast.makeText(MainActivity.this, "null값이 뜨고 있어서 그런걸까?ㅣㅏ", Toast.LENGTH_SHORT).show();
            }

            // IRC 서버로 메시지를 보냄.
            client.sendMessage(channel, message); // 매개변수로 전달받은 channel을 사용 ===>현재 여기서 오류 발생중. 서버 연결이 안되고 있기 떄문이다.

            // EditText 내용을 지움.
            chat_edt.setText("");
        }


        @Override
        protected void onDestroy () {
            super.onDestroy();
            client.stopBotReconnect();
            client.sendIRC().quitServer();
        }
    }

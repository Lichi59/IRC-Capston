package com.example.capston_irc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import com.example.capston_irc.MainActivity;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 인텐트에서 메세지를 가져온다.
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // TextView를 만들어 메세지를 보여준다.
        TextView chat_tv = findViewById(R.id.chat_tv);
        chat_tv.setText(message);
    }
}

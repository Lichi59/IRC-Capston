package com.example.capston_irc;

import static com.example.capston_irc.MainActivity.EXTRA_MESSAGE;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NicknameActivity extends AppCompatActivity {
    Button save_btn;
    EditText nick_edt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nickname);

        // 버튼 및 EditText 객체 가져오기
        save_btn = findViewById(R.id.save_btn);
        nick_edt = findViewById(R.id.nick_edt);

        // 버튼에 클릭 리스너 설정하기
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // EditText에서 닉네임 가져오기
                String nickname = nick_edt.getText().toString();

                // Intent를 통해 닉네임 값을 MainActivity에 전달하고 MainActivity 실행하기
                Intent intent = new Intent(NicknameActivity.this, MainActivity.class);
                intent.putExtra("com.example.capston_irc.nickname", nickname);
                
                //MainActivity로 전환
                startActivity(intent);//아니 왜 안되냐고
                finish();
            }
        });
    }
}

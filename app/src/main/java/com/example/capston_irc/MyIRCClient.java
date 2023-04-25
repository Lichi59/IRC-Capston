package com.example.capston_irc;

import static android.service.controls.ControlsProviderService.TAG;

import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.User;
import org.pircbotx.exception.IrcException;
import java.io.IOException;
import org.pircbotx.output.OutputIRC;

import java.io.IOException;

public class MyIRCClient extends PircBotX {
    private String channel;
    private OutputIRC output;
    public MyIRCClient(Configuration.Builder configurationBuilder, String channel) throws IOException, IrcException {
        super(configurationBuilder.buildConfiguration());
        this.channel = channel;
        this.output = new OutputIRC(this);
    }

    public void onMessage(String channel, User user, String message) {
        System.out.println(user.getNick() + " : " + message);
        // 사용자의 닉네임과 메시지를 출력
        // 이 코드를 UI 스레드에서 실행하기 위해서는 runOnUiThread()를 사용해야 한다고 하는데 잘 모르겠음
    }

    public void startBot() throws IOException, IrcException {
        this.connect();
    }

    public void stopBotReconnect() {
    }

    public OutputIRC sendIRC(String channel, String message) {
        // IRC 서버로 채널과 메시지를 전송하고, 결과를 OutputIRC 객체로 반환
        this.output.message(channel, message);
        return this.output;
    }

    public void sendMessage(String channel, String message) {
        // IRC 서버로 채널과 메시지를 전송
        if (!isConnected()) {
        }
        sendIRC(channel, message);
    }

    public String getNickname() {
        return null;
    }

    public boolean isConnected() {
        return super.isConnected() && getClient().getUserChannelDao().containsChannel(channel); // getClient()는 PircBotX의 `getBot()` 메서드와 같은 역할
    }
    public PircBotX getClient() {
        return this;
    }
}



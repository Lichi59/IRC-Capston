package com.example.capston_irc;
import android.app.Activity;
import android.widget.TextView;
import android.widget.Toast;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;
import java.io.IOException;

public class MyIRCClient extends ListenerAdapter {
    private PircBotX bot;
    private TextView chatView;
    //스레드 시작코드
//    ConnectThread connectThread = new ConnectThread("irc.freenode.net", 6667);
//    connectThread.start();


    public MyIRCClient() throws Exception {
        this.chatView = chatView;

        Configuration config = new Configuration.Builder()
                .setName("MyNickname")
                .setLogin("MyUsername")
                .addServer("irc.freenode.net", 6667)
                .addAutoJoinChannel("#MyChannel")
                .addListener(this)
                .buildConfiguration();

        bot = new PircBotX(config);
    }

    public void connect() throws IOException, IrcException {
        bot.startBot();
    }

    @Override
    public void onMessage(MessageEvent event) throws Exception {
        chatView.append(event.getUser().getNick() + ": " + event.getMessage() + "\n");
    }

}

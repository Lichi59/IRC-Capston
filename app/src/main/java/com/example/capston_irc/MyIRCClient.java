package com.example.capston_irc;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.User;
import org.pircbotx.cap.EnableCapHandler;
import org.pircbotx.exception.IrcException;
import org.pircbotx.hooks.Event;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.managers.ListenerManager;

import java.io.IOException;
import android.app.Activity;
import android.widget.TextView;
import android.widget.Toast;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.output.OutputIRC;

import java.io.IOException;

public class MyIRCClient extends PircBotX {
    public MyIRCClient(Configuration configuration) {
        super(configuration);
    }
    public MyIRCClient(Configuration.Builder configurationBuilder) throws IOException, IrcException {
        super(configurationBuilder.buildConfiguration());
    }

    public void onMessage(String channel, User user, String message) {
        System.out.println(user.getNick() + " : " + message);
    }

    public void startBot() throws IOException, IrcException {
        this.connect();
    }

    public void stopBotReconnect() {
    }

    public OutputIRC sendIRC() {
        return null;
    }

    public void sendMessage(String channel, String message) {
    }

    public String getNickname() {
        return null;
    }
}


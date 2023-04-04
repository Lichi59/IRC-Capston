package com.example.capston_irc;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import org.pircbotx.exception.IrcException;
import java.net.UnknownHostException;

public class ConnectThread extends Thread {
    private String hostname;
    private int port;
    private String serverAddress;
    private int serverPort;
    private String nickname;

    public ConnectThread(String serverAddress, int serverPort, String nickname) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
        this.nickname = nickname;
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket(hostname, port);

            // 소켓 연결 후에는 소켓으로부터 InputStream과 OutputStream을 얻어올 수 있다.
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            // 이제부터는 inputStream과 outputStream을 이용하여 IRC 서버와 통신
            // 예를 들어 outputStream.write("USER username 0 * :realname\r\n".getBytes()); 와 같이 메시지를 전송

        } catch (UnknownHostException e) {
            // 호스트 이름을 알 수 없는 경우 발생하는 예외
            e.printStackTrace();
        } catch (IOException e) {
            // IO 오류가 발생한 경우 발생하는 예외
            e.printStackTrace();
        }
    }
}

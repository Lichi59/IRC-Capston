package com.example.capston_irc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import org.pircbotx.exception.IrcException;
import java.net.UnknownHostException;

public class ConnectThread extends Thread {
    private String serverAddress;
    private int serverPort;
    private String nickname;
    String username;
    String realname;

    public ConnectThread(String serverAddress, int serverPort, String nickname) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
        this.nickname = nickname;
        this.username = username;
        this.realname = realname;
    }

    @Override
    public void run() {
        try {
            //소켓 연결
            Socket socket = new Socket(serverAddress, serverPort);

            // 소켓 연결 후에는 소켓으로부터 InputStream과 OutputStream을 얻어올 수 있다.
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            // IRC 서버에 접속한 후, NICK 메시지와 USER 메시지를 전송
            String nickMessage = "NICK " + nickname + "\r\n";
            String userMessage = "USER " + username + " 0 * :" + realname + "\r\n";
            outputStream.write(nickMessage.getBytes());
            outputStream.write(userMessage.getBytes());

            // 소켓으로부터 읽어들일 BufferedReader 객체 생성
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            // IRC 서버로부터 응답을 계속해서 받아서 처리
            //공사중
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // IRC 서버에서 받은 응답을 처리하는 코드
                // 메시지가 PRIVMSG인 경우에는 닉네임과 메시지를 파싱하여 MainActivity에서 TextView에 출력하는 코드를 작성할 수 있다고 한다
                //개어렵네
            }

        } catch (UnknownHostException e) {
            // 호스트 이름을 알 수 없는 경우 발생하는 예외
            e.printStackTrace();
        } catch (IOException e) {
            // IO 오류가 발생한 경우 발생하는 예외
            e.printStackTrace();
        }
    }
}

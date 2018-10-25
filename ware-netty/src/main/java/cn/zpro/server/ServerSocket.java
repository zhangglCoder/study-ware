package cn.zpro.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 *
 * @author zhanggl
 */
public class ServerSocket {

    public static void main(String[] args) throws IOException {
        java.net.ServerSocket serverSocket = new java.net.ServerSocket(8888);

        while (true){
            Socket accept = serverSocket.accept();
            InputStream inputStream = accept.getInputStream();

        }
    }
}

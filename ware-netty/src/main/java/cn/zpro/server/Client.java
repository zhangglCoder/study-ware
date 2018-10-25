package cn.zpro.server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author zhanggl
 */
public class Client {

    public static void main(String[] args) throws IOException {
        Socket socket=new Socket("127.0.0.1",4700);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.flush();

    }
}

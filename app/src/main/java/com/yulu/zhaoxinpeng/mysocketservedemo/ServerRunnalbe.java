package com.yulu.zhaoxinpeng.mysocketservedemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by Administrator on 2017/4/19.
 * 单独处理客户端Socket的通讯
 */

public class ServerRunnalbe implements Runnable {

    private Socket mSocket = null;
    private BufferedReader mBufferedReader;

    public ServerRunnalbe(Socket socket) throws IOException {
        this.mSocket = socket;

        // 3.1 打开输入流
        mBufferedReader = new BufferedReader(new InputStreamReader(mSocket.getInputStream(), "utf-8"));
    }

    @Override
    public void run() {

        try {

            String content = null;

            // 4.1 读取客户端传递的消息
            while ((content = mBufferedReader.readLine()) != null) {
                // 读取的
                System.out.println(content);

                // 3.2 打开输出流
                OutputStream outputStream = mSocket.getOutputStream();

                // 当客户端发送一个bye，通讯结束
                if ("bye".equals(content)) {
                    outputStream.write(("通话结束了").getBytes("utf-8"));

                    // 5. 关闭Socket
                    outputStream.close();
                    mBufferedReader.close();
                    mSocket.close();
                    return;
                }

                // 4.2 写入消息给客户端
                outputStream.write((content + "这是服务端返回的。\n").getBytes("utf-8"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

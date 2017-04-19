package com.yulu.zhaoxinpeng.mysocketservedemo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Administrator on 2017/4/19.
 * 服务端
 * 1. 创建一个服务端的Socket
 * 2. 接收连接到此服务端的客户端Socket
 * 3. 打开连接的客户端的Socket的输入输出流
 * 4. 对Socket进行读/写的操作
 * 5. 关闭连接的客户端Socket
 */
public class MyServe {

    private static ServerSocket mServerSocket;

    //程序的入口：main方法
    public static void main(String[] args) throws IOException {

        //1. 创建Socket 的服务端
        mServerSocket = new ServerSocket(3600);

        //2. 接收连接的客户端
        while (true){

            //获取连接的 Socket
            Socket mSocket = mServerSocket.accept();

            System.out.println("connect success");

            //为每一个连接的 Socket开启一个单独的线程去处理
            new Thread(new ServerRunnalbe(mSocket)).start();
        }
    }
}

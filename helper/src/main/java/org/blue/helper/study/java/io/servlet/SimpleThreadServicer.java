package org.blue.helper.study.java.io.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * 简单多线程服务器 功能就是服务器端监听8088端口 读到什么数据，就向客户端回写什么数据
 */
public class SimpleThreadServicer {
    private static ExecutorService pool = new ThreadPoolExecutor(10,
                                                                              Integer.MAX_VALUE,
                                                                             60L,
                                                                              TimeUnit.SECONDS,
                                                                             new LinkedBlockingDeque<>(20));

    public static void main(String[] args) throws Exception {
        ServerSocket echoServer = null;
        Socket clientSocket = null;
        try {
            echoServer = new ServerSocket(8088);
        } catch (IOException e) {
            System.out.println(e);
        }
        while (true) {
            try {
                clientSocket = echoServer.accept();
                System.out.println(clientSocket.getRemoteSocketAddress() + " connect!");
                pool.execute(new HandleMsg(clientSocket));
            } catch (IOException e) {
                System.out.println(e);
            }finally {
                clientSocket.close();
            }
        }
    }

    static class HandleMsg implements Runnable {
        private Socket clientSocket;

        public HandleMsg(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        //省略部分信息
        public void run() {
            try (BufferedReader is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter os = new PrintWriter(clientSocket.getOutputStream(), true);
            ) {
                // 从InputStream当中读取客户端所发送的数据              
                String inputLine = null;
                long b = System.currentTimeMillis();
                while ((inputLine = is.readLine()) != null) {
                    os.println(inputLine);
                }
                long e = System.currentTimeMillis();
                System.out.println("spend:" + (e - b) + " ms ");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

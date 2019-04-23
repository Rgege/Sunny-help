package org.blue.helper.study.java.io.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class SimpleThreadClient {

    public static void main(String[] args) {
        PrintWriter writer = null;
        BufferedReader reader = null;
        try (
                Socket client = new Socket();
        ) {
            client.connect(new InetSocketAddress("localhost", 8000));
            writer = new PrintWriter(client.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            writer.println("Hello!");
            writer.flush();
            System.out.println("from server: " + reader.readLine());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) writer.close();
                if (reader != null) reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

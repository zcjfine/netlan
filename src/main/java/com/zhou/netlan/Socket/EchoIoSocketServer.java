package com.zhou.netlan.Socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class EchoIoSocketServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5552);
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        while(true) {
            Socket socket  = serverSocket.accept();
            System.out.println("accepted.. "+socket);
            executorService.submit(new EchoIoSocketHandle(socket));
        }
    }
    static class EchoIoSocketHandle implements Runnable {
        private Socket socket;

        public EchoIoSocketHandle(Socket socket) {
            this.socket=socket;
        }

        @Override
        public void run() {
            try {
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("utf-8")));
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,Charset.forName("utf-8")));
                while (true) {
                    String s = bufferedReader.readLine();
                    if ("exit".equals(s)) {
                        break;
                    }
                    System.out.println(socket+" echo:"+s);
                    bufferedWriter.write(s);
                    bufferedWriter.write(System.lineSeparator());
                    bufferedWriter.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

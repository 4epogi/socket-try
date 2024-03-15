package dev.chepogi.http;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;

public class HttpServer {
    private final int port;

    public HttpServer(int port) {
        this.port = port;
    }

    public void run() {
        try (var serverSocket = new ServerSocket(port);
             Socket socket = serverSocket.accept()) {
            processSocket(socket);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void processSocket(Socket socket) {
        try (InputStream inputStream = new DataInputStream(socket.getInputStream());
             OutputStream outputStream = new DataOutputStream(socket.getOutputStream())) {

            System.out.println(new String(inputStream.readNBytes(150)));

            byte[] body = Files.readAllBytes(Path.of("src/main/resources/example.html"));

            outputStream.write("""
                    HTTP/1.1 200 OK
                    content-type: text/html
                    content-length: 100  
                    """.formatted(body.length).getBytes());
            outputStream.write(System.lineSeparator().getBytes());
            outputStream.write(body);
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}

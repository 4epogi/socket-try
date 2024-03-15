package dev.chepogi.http;

public class HttpServerRunner {
    public static void main(String[] args) {
        HttpServer server = new HttpServer(8082);
        server.run();
    }
}

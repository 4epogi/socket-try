import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;

public class SocketServer {
    public static void main(String[] args) {
        try(var serverSocket = new ServerSocket(8081);
        var socket = serverSocket.accept();
            var inputStream = new DataInputStream(socket.getInputStream());
            var outputStream = new DataOutputStream(socket.getOutputStream())) {

            System.out.println("Клиент: " + inputStream.readUTF());
            outputStream.writeUTF("Привет от сервера");

        } catch (IOException ex) {

        }
    }
}

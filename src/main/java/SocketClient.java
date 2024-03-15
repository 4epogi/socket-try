import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SocketClient {
    public static void main(String[] args) {
        try(var socket = new Socket("localhost", 8081);
            var inputStream = new DataInputStream(socket.getInputStream());
            var outputStream = new DataOutputStream(socket.getOutputStream())
        ){

            outputStream.writeUTF("Привет от клиента");
            System.out.println("Сервер: " + inputStream.readUTF());

        } catch (IOException ex) {
        }
    }
}

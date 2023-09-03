import com.philimonov.entity.Phone;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8000)) {
            System.out.println("Server started!!!");

            while (true) {
                Phone phone = new Phone(serverSocket);
                try (phone) {
                    String request = phone.readLine();
                    System.out.println("Request: " + request);
                    String response = String.valueOf((int) (Math.random() + 30 - 10));
                    System.out.println("Response: " + response);
                    phone.writeLine(response);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

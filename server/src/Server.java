import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8000)) {
            System.out.println("Server started!!!");

            while (true) {
                try (Socket socket = serverSocket.accept();
                     OutputStream stream = socket.getOutputStream();
                     //читать от клиента
//        socket.getInputStream()
                     //получим OutputStreamWriter
                     BufferedWriter writer =
                             new BufferedWriter(
                                     new OutputStreamWriter(
                                             socket.getOutputStream()));
                     BufferedReader reader = new BufferedReader(
                             new InputStreamReader(
                                     socket.getInputStream()))) {
                    String request = reader.readLine();
                    System.out.println("Request: " + request);
                    String response = String.valueOf((int) (Math.random() + 30 - 10));
                    System.out.println("Response: " + response);
                    writer.write(response);
                    writer.newLine();
                    writer.flush();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

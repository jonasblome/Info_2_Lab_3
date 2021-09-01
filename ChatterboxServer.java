package lab3;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ChatterboxServer {
  Socket socket;

  public static void main (String[] args) throws IOException{
    ChatterboxServer server = new ChatterboxServer();
    server.start();
  }

  public void start() {
    try {
      ServerSocket serverSocket = new ServerSocket(8050);
      System.out.println("Server starting up...");
      socket = serverSocket.accept();
      System.out.println("Client connected to Server.");

      listen();
      serverSocket.close();
    }
    catch (IOException e) {
      System.out.println("Cannot create ServerSocket");
    }
  }

  public void listen(){
    try {
      InputStreamReader in = new InputStreamReader(socket.getInputStream());
      Scanner scanner = new Scanner(in);

      String str = "";

      while(!str.equals("stop")) {
        str = scanner.nextLine();
        System.out.println("Client says: " + str + "\r\n");
      }
      scanner.close();
    }
    catch (IOException e) {
      System.out.println("Cannot access server input stream.");
    }
  }
}

package lab3;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatterboxClient {
  Socket socket;

  public static void main (String[] args){
    ChatterboxClient chatterboxClient = new ChatterboxClient();
    chatterboxClient.start();
  }

  public void start() {
    try {
      socket = new Socket("127.0.0.1", 8050);
      //listen();
      write();
    }
    catch (IOException e) {
      System.out.println("Cannot connect to server.");
    }
  }

  public void write() {
    try {
      PrintWriter printWriter = new PrintWriter(socket.getOutputStream());

      printWriter.print("test123\r\n");
      printWriter.flush();

      Scanner scanner = new Scanner(System.in);

      while (scanner.hasNextLine()) {
        String nextLine = scanner.nextLine();
        printWriter.print(nextLine + "\r\n");
        printWriter.flush();
      }
      scanner.close();
      socket.close();
    }
    catch (IOException e) {
      System.out.println("Couldn't get output stream");
    }
  }

  public void listen() {
    try {
      Scanner serverScanner = new Scanner(socket.getInputStream());
      while (serverScanner.hasNextLine()) {
        String nextLine = serverScanner.nextLine();
        System.out.println("Server says: " + nextLine);
      }
    }
    catch (IOException e) {
      System.out.println("Couldn't get input stream");
    }
  }
}

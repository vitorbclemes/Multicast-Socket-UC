import java.io.*;
import java.net.*;
public class Gerador {
  public static void main(String[] args) {
    DatagramSocket socket = null;
    DatagramPacket outPacket = null;
    byte[] outBuf;
    final int PORT;
 
    try {
      socket = new DatagramSocket();
      String msg;
      PORT = Integer.parseInt(args[0]);
      msg = args[1];
      outBuf = msg.getBytes();

      //Send to multicast IP address and port
      //InetAddress address = InetAddress.getByName("224.0.0.1");
      InetAddress address = InetAddress.getByName("224.0.0.2");
      outPacket = new DatagramPacket(outBuf, outBuf.length, address, PORT);

      socket.send(outPacket);

      System.out.println("Server sends : " + msg);
      try {
        Thread.sleep(500);
      } catch (InterruptedException ie) {
      }

    } catch (IOException ioe) {
      System.out.println(ioe);
    }
  }
}

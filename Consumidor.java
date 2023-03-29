import java.io.*;
import java.net.*;

public class Consumidor {
  public static void main(String[] args) {
    MulticastSocket socket = null;
    DatagramPacket inPacket = null;
    byte[] inBuf = new byte[256];
    int nmrGeradores;

    try {
      //Prepare to join multicast group
      socket = new MulticastSocket(Integer.parseInt(args[0]));
      //InetAddress address = InetAddress.getByName("224.0.0.1");      
      InetAddress address = InetAddress.getByName("224.0.0.2");
      
      socket.joinGroup(address);
      
      nmrGeradores = Integer.parseInt(args[1]);
      for(int i = 0; i < nmrGeradores;i++) {
        inPacket = new DatagramPacket(inBuf, inBuf.length);
        socket.receive(inPacket);
        String msg = new String(inBuf, 0, inPacket.getLength()).toUpperCase();
        System.out.println("From " + inPacket.getAddress() + " Msg : " + msg);
      }
    } catch (IOException ioe) {
      System.out.println(ioe);
    }
  }
}

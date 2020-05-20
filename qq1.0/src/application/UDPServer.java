package application;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import java.util.HashSet;
import java.util.Set;

public class UDPServer {

	//用于存储在线客户端口号的hashset
	public static Set<Integer> portSet = new HashSet<Integer>();
	
	public static void main(String[] args) throws IOException {
		
		DatagramSocket datagramSocket = new DatagramSocket(8000);
        
        while(true){
        	//接收消息
        	byte b[]=new byte[1024];
	        DatagramPacket datagramPacket = new DatagramPacket(b, b.length);
	        datagramSocket.receive(datagramPacket);
	       
	        //解析端口号、ip
	        int senderPort=datagramPacket.getPort();
	        InetAddress senderAddress=datagramPacket.getAddress();
	        
	        //判断是否为用户退出消息
	        if(new String(b).indexOf("Quit")!=-1) {
	        	portSet.remove(senderPort);
	        }
	        else {
	        	portSet.add(senderPort);
	        }
	        
	        //服务器端输出在线客户端数量、当前消息对应客户端ip、端口号
	        System.out.println("客户端数量："+portSet.size());
	        System.out.println("ip"+senderAddress+"port"+senderPort+new String(b));
	        
	        //服务器端转发消息
        	for(int port : portSet) {
	        	DatagramPacket datagramPacket2 = new DatagramPacket(b, b.length, senderAddress, port);
		        datagramSocket.send(datagramPacket2);
	        }
	        
        }

    }

}

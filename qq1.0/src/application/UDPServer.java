package application;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import java.util.HashSet;
import java.util.Set;

public class UDPServer {

	//���ڴ洢���߿ͻ��˿ںŵ�hashset
	public static Set<Integer> portSet = new HashSet<Integer>();
	
	public static void main(String[] args) throws IOException {
		
		DatagramSocket datagramSocket = new DatagramSocket(8000);
        
        while(true){
        	//������Ϣ
        	byte b[]=new byte[1024];
	        DatagramPacket datagramPacket = new DatagramPacket(b, b.length);
	        datagramSocket.receive(datagramPacket);
	       
	        //�����˿ںš�ip
	        int senderPort=datagramPacket.getPort();
	        InetAddress senderAddress=datagramPacket.getAddress();
	        
	        //�ж��Ƿ�Ϊ�û��˳���Ϣ
	        if(new String(b).indexOf("Quit")!=-1) {
	        	portSet.remove(senderPort);
	        }
	        else {
	        	portSet.add(senderPort);
	        }
	        
	        //��������������߿ͻ�����������ǰ��Ϣ��Ӧ�ͻ���ip���˿ں�
	        System.out.println("�ͻ���������"+portSet.size());
	        System.out.println("ip"+senderAddress+"port"+senderPort+new String(b));
	        
	        //��������ת����Ϣ
        	for(int port : portSet) {
	        	DatagramPacket datagramPacket2 = new DatagramPacket(b, b.length, senderAddress, port);
		        datagramSocket.send(datagramPacket2);
	        }
	        
        }

    }

}

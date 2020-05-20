package application;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import javafx.scene.control.TextArea;

public class messageThread implements Runnable {
	
	private TextArea outputTextArea;
	
	private DatagramSocket datagramSocket;
	
	public messageThread(DatagramSocket datagramSocket, TextArea output) {
		this.datagramSocket = datagramSocket;
		this.outputTextArea = output;
	}

	//监听来自服务器端转发的消息并显示在输出区
	@Override
	public void run() {
		
        while(true){
        	byte b[]=new byte[1024];
	        DatagramPacket datagramPacket = new DatagramPacket(b, b.length);

	        try {
				datagramSocket.receive(datagramPacket);
			} catch (IOException e) {
				e.printStackTrace();
			}
	        
	        outputTextArea.appendText(new String(b)+"\n");
	        
        }
		
	}

}

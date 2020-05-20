package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.event.ActionEvent;

public class configController {
	
	@FXML
	private Button send;
	@FXML
	private Button start;
	@FXML
	private Button end;
	@FXML
	private TextArea input;
	@FXML
	private TextArea outputTextArea;
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	//开始聊天按钮
	@FXML
	public void startBtn(ActionEvent e) throws IOException {
		
		InetAddress byName = InetAddress.getByName("127.0.0.1");
		
		new Thread(new messageThread(Main.datagramSocket, outputTextArea)).start();
		
		String login="["+dateFormat.format(new Date())+"]"+"+++用户： "+Main.username+" Enter+++";
	    byte user_in[]=login.getBytes();
	    
	    DatagramPacket datagramPacket_user_in1 = new DatagramPacket(user_in, user_in.length, byName, 8000);    
        Main.datagramSocket.send(datagramPacket_user_in1);
        
	}
	
	//结束聊天按钮
	@FXML
	public void endBtn(ActionEvent e) throws Exception {
		
		InetAddress byName = InetAddress.getByName("127.0.0.1");
		String logout="["+dateFormat.format(new Date())+"]"+"+++用户： "+Main.username+" Quit+++";
        byte user_out[]=logout.getBytes();
        
        //在当前用户输出窗口提示退出内容
        outputTextArea.appendText(logout+"\n");
        
        DatagramPacket datagramPacket_user_out1 = new DatagramPacket(user_out, user_out.length, byName, 8000);    
        Main.datagramSocket.send(datagramPacket_user_out1);

    }
	
	//发送按钮：将消息发送至服务器端
	@FXML
	public void eventButton1(ActionEvent event) throws Exception{
		
		InetAddress byName = InetAddress.getByName("127.0.0.1");
		String str1=input.getText();
		
		String str="["+dateFormat.format(new Date())+"]"+"用户"+Main.username+":  "+str1;
	    byte b[]=str.getBytes();
	    DatagramPacket datagramPacket1 = new DatagramPacket(b, b.length, byName, 8000);    
	    Main.datagramSocket.send(datagramPacket1);
	    
	    //发送完成后，清空输入区
		input.setText("");;
		
	}
	
}

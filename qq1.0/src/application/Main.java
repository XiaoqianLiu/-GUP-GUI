package application;
	
import java.net.DatagramSocket;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	public static String username = null;
	public static DatagramSocket datagramSocket;
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Scene scene = new Scene(root,400,239);
            scene.getStylesheets().add(getClass().getResource("./application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);//设置不能窗口改变大小
            primaryStage.setTitle("QQ");//设置标题

    		datagramSocket = new DatagramSocket();
    		primaryStage.show();
            
            
		} catch(Exception e) {
			e.printStackTrace();
		}
			
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}



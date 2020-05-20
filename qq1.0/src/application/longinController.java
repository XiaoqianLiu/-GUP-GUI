package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class longinController {
	private Stage loginStage;
	@FXML
	private TextField nameTextField;
	@FXML
	private AnchorPane rootLayout;
	
	@FXML
	public void loginButtonEvent(ActionEvent event) throws Exception {
		Main.username = nameTextField.getText();
		application.second second = new second();
		second.showWindow();
		loginStage=(Stage) rootLayout.getScene().getWindow();
        loginStage.close();
	}
}

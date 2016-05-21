package hu.popcornmaci.gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainmenuController {
	@FXML
	private Button newShoppingButton;
	@FXML
	private Button calcButton;
	
	@FXML
	private void newShoppingButtonAction() throws IOException{
        Stage stage;
        Parent root;
        stage = (Stage) calcButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/NewShopping.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

	}
	@FXML
	private void calcButtonAction() throws IOException{
        Stage stage;
        Parent root;
        stage = (Stage) calcButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Calculator.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}
}

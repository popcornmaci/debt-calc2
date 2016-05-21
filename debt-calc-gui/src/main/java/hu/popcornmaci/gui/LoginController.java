package hu.popcornmaci.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import hu.popcornmaci.dao.entity.Person;
import hu.popcornmaci.service.api.LoginException;
import hu.popcornmaci.service.api.LoginService;
import hu.popcornmaci.service.impl.LoginServiceImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class LoginController implements Initializable{
	@FXML
	private TextField userNameField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private Button loginButton;
	@FXML
	private Button regButton;
	private LoginService loginS;
	@FXML
	private void onRegAction() throws IOException{
            Stage stage;
            Parent root;
            stage = (Stage) regButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Reg.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

	}
	@FXML
	private void onLoginAction() throws IOException{
		try {
			Person login = loginS.login(userNameField.getText(), passwordField.getText());
			LoginInfo.setPerson(login);
			Stage stage;
            Parent root;
            stage = (Stage) loginButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Mainmenu.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
		} catch (LoginException e) {
			new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK).show();
		}
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loginS = new LoginServiceImpl();
	}
}

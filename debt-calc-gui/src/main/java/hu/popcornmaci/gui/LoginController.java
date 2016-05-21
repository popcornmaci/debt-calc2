package hu.popcornmaci.gui;

/*
 * #%L
 * debt-calc-gui
 * %%
 * Copyright (C) 2016 Faculty of Informatics, University of Debrecen
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */


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
            stage.setTitle(String.format("Szia %s!", LoginInfo.getPerson().getFullName()));
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

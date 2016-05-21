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

import hu.popcornmaci.service.api.RegException;
import hu.popcornmaci.service.api.RegService;
import hu.popcornmaci.service.impl.RegServiceImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegController implements Initializable{
	@FXML
	private TextField fullNameField;
	@FXML
	private TextField userNameField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private Button cancelButton;
	@FXML
	private Button regButton;
	private RegService regS;
	private Scene parentScene;
	@FXML
	private void onRegAction() throws IOException{
		try {
			regS.register(fullNameField.getText(), userNameField.getText(), passwordField.getText());
            Stage stage;
            Parent root;
            stage = (Stage) regButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Login.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
		}catch (RegException e) {
			new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK).show();
		}
	}
	@FXML 
	private void cancelButtonAction() throws IOException{
        Stage stage;
        Parent root;
        stage = (Stage) regButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Login.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		regS = new RegServiceImpl();
	}
	public Scene getParentScene() {
		return parentScene;
	}
	public void setParentScene(Scene parentScene) {
		this.parentScene = parentScene;
	}
	
}

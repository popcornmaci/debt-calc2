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

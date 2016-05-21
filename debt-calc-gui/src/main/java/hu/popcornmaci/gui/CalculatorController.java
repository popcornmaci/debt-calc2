package hu.popcornmaci.gui;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Map;
import java.util.ResourceBundle;

import hu.popcornmaci.dao.entity.Person;
import hu.popcornmaci.service.api.DebtService;
import hu.popcornmaci.service.impl.DebtServiceImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CalculatorController implements Initializable {
	@FXML
	private DatePicker dateFrom;
	@FXML
	private DatePicker dateTo;
	@FXML
	private Button okButton;
	@FXML
	private Button backButton;
	@FXML
	private VBox debtTo;
	@FXML
	private VBox debtFrom;
	private DebtService debts;

	@FXML
	private void okButtonAction() {
		if(dateFrom.getValue()!=null && dateTo.getValue()!=null && dateFrom.getValue().isAfter(dateTo.getValue())){
			LocalDate temp;
			temp = dateFrom.getValue();
			dateFrom.setValue(dateTo.getValue());
			dateTo.setValue(temp);
		}
		Map<Person, Double> debtFromMap = debts.getDebtFrom(LoginInfo.getPerson(), dateFrom.getValue(),
				dateTo.getValue());
		Map<Person, Double> debtToMap = debts.getDebtTo(LoginInfo.getPerson(), dateFrom.getValue(), dateTo.getValue());
		fillVbox(debtFromMap,debtFrom);
		fillVbox(debtToMap,debtTo);
	}
	

	private void fillVbox(Map<Person, Double> map, VBox vb) {
		vb.getChildren().clear();
		if(map==null || map.isEmpty()){
			Label label = new Label("Nincs megjelenítendő adat");
			vb.getChildren().add(label);
			return;
		}
		for (Person person : map.keySet()) {
			Label label = new Label(String.format("%s: %.2f", person.getFullName(),map.get(person)));
			vb.getChildren().add(label);
		}
		
	}


	@FXML
	private void backButtonAction() throws IOException {
		Stage stage;
		Parent root;
		stage = (Stage) backButton.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Mainmenu.fxml"));
		root = loader.load();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		debts = new DebtServiceImpl();
	}

}

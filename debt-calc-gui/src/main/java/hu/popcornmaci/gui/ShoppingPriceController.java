package hu.popcornmaci.gui;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import hu.popcornmaci.dao.entity.Item;
import hu.popcornmaci.dao.entity.PayInfo;
import hu.popcornmaci.dao.entity.Person;
import hu.popcornmaci.dao.entity.Shopping;
import hu.popcornmaci.service.api.ShoppingService;
import hu.popcornmaci.service.impl.ShoppingServiceImpl;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ShoppingPriceController implements Initializable {

	@FXML
	private Label totalPrice;
	@FXML
	private Label remainingPrice;
	@FXML
	private VBox vbox;
	@FXML
	private Button backButton;
	@FXML
	private Button nextButton;
	private Shopping shopping;
	private Map<Person, Double> payMap;
	private Double total;
	private ShoppingService shs;

	public void initShopping(Shopping sh) {
		shopping = sh;
		List<Person> persons = shopping.getItems().stream().flatMap(i -> i.getPersons().stream()).distinct()
				.collect(Collectors.toList());
		for (Person person : persons) {
			HBox hbox = new HBox(5.0);
			TextField tfield = new TextField("0");
			tfield.setPrefWidth(60.0);
			payMap.put(person, 0.0);
			tfield.textProperty().addListener(new ChangeListener<String>() {
				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					if (newValue == null || newValue.equals("")) {
						return;
					}
					if (newValue.indexOf('.') != newValue.lastIndexOf('.')) {
						newValue = newValue.substring(0, newValue.length() - 1);
					}
					if (!newValue.matches("\\d+\\.?\\d?\\d?")) {
						// newValue = newValue.replaceAll("[^\\d\\.]", "");
						newValue = oldValue == null ? "" : oldValue;
					}
					tfield.setText(newValue);

					payMap.put(person, Double.parseDouble(tfield.getText()));
					refreshRemaining();
				}
			});
			Label label = new Label(String.format("%s (%s):", person.getFullName(), person.getUsername()));
			label.setPrefWidth(190.0);
			hbox.getChildren().addAll(label, tfield);
			vbox.getChildren().add(hbox);

			total = shopping.getItems().stream().mapToDouble(Item::getValue).sum();
			totalPrice.setText(String.format("Végösszeg: %.2f", total));
			refreshRemaining();
		}
	}

	private void refreshRemaining() {
		double rem = getRemaining();
		remainingPrice
				.setText(String.format("Fennmaradó összeg: %.2f", Double.compare(total, rem) == 0 ? 0.0 : total - rem));
	}

	private double getRemaining() {
		return payMap.values().stream().mapToDouble(v -> v).sum();
	}

	@FXML
	private void backButtonAction() throws IOException {
		Stage stage;
		Parent root;
		stage = (Stage) backButton.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/NewShopping.fxml"));
		root = loader.load();
		RegController controller = loader.getController();
		controller.setParentScene(stage.getScene());
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	private void nextButtonAction() throws IOException {
		if (Double.compare(getRemaining(), total) != 0) {
			new Alert(AlertType.ERROR, "Nincs kiegyenlítve a számla", ButtonType.OK).show();
			return;
		}
		List<PayInfo> pinfo = payMap.keySet().stream().map(k -> new PayInfo(shopping, k, payMap.get(k)))
				.collect(Collectors.toList());
		shopping.setPinfo(pinfo);
		shs.save(shopping);

		Stage stage;
		Parent root;
		stage = (Stage) nextButton.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Mainmenu.fxml"));
		root = loader.load();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		payMap = new HashMap<>();
		shs = new ShoppingServiceImpl();
	}

}

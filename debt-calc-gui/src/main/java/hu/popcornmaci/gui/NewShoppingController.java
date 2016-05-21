package hu.popcornmaci.gui;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;

import hu.popcornmaci.dao.entity.Item;
import hu.popcornmaci.dao.entity.Person;
import hu.popcornmaci.dao.entity.Shopping;
import hu.popcornmaci.service.api.PersonService;
import hu.popcornmaci.service.impl.PersonServiceImpl;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NewShoppingController implements Initializable {
	@FXML
	private DatePicker date;
	@FXML
	private TextField itemName;
	@FXML
	private TextField itemPrice;
	@FXML
	private TextField usernames;
	@FXML
	private VBox vbox;
	@FXML
	private Button okButton;
	@FXML
	private Button backButton;
	@FXML
	private Button nextButton;
	private List<Item> itemList;

	@FXML
	private void okButtonAction() {
		if (itemName.getText().equals("") || itemPrice.getText().equals("")
				|| usernames.getText().equals("")) {
			new Alert(AlertType.ERROR, "Minden mező kitöltése kötelező", ButtonType.OK).show();
			return;
		}

		String[] usernamesplit = usernames.getText().split(",");
		Set<Person> persons = new HashSet<>();
		for (String username : usernamesplit) {
			String trim = username.trim();
			if (trim.equals("")) {
				continue;
			}
			PersonService ps = new PersonServiceImpl();
			Person person = ps.getPerson(trim);
			if (person == null) {
				new Alert(AlertType.ERROR, "Nem létezik ilyen felhasználónév: " + trim, ButtonType.OK).show();
				return;
			}
			persons.add(person);
		}
		Item item = new Item(itemName.getText(), Double.parseDouble(itemPrice.getText()), new ArrayList<>(persons));
		itemList.add(item);
		addLabel(item);
	}

	private void addLabel(Item item) {
		vbox.getChildren().add(new Label(String.format("%s: %.2f\t%s", item.getItemName(), item.getValue(),
				item.getPersons().stream().map(p -> p.getFullName()).collect(Collectors.joining(", ")))));
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

	@FXML
	private void nextButtonAction() throws IOException {
		if (date.getValue() == null || date.getValue().isAfter(LocalDate.now())) {
			new Alert(AlertType.ERROR, "Hiányzó dátum/Mai dátumnál nem lehet későbbi a dátum", ButtonType.OK).show();
			return;
		}
		Stage stage;
		Parent root;
		stage = (Stage) nextButton.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/ShoppingPrice.fxml"));
		root = loader.load();	
		ShoppingPriceController ctrl = loader.getController();
		ctrl.initShopping(new Shopping(Date.from(date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
				itemList, "",null));

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		itemList = new ArrayList<>();
		itemPrice.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(newValue==null || newValue.equals("")){
					return;
				}
				if(newValue.indexOf('.')!=newValue.lastIndexOf('.')){
					newValue=newValue.substring(0,newValue.length()-1);
				}
				if (!newValue.matches("\\d+\\.?\\d?\\d?")) {
					//newValue=newValue.replaceAll("[^\\d\\.]", "");
					newValue=oldValue==null?"":oldValue;
				}
				itemPrice.setText(newValue);
			}
		});
		date.setValue(LocalDate.now());
	}
}

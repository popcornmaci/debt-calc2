package hu.popcornmaci;

import hu.popcornmaci.dao.EFMManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    
    public static String name;
    
    @Override
    public void start(Stage stage) throws Exception {
        name = "asd";
        Parent root = FXMLLoader.load(getClass().getResource("/Login.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e->EFMManager.close()); 
    }
    public static void main(String[] args) {
        EFMManager.getManager();
    	launch(args);
    	
    }
    

}

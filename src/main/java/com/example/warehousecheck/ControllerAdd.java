package com.example.warehousecheck;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControllerAdd {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addButton;

    @FXML
    private Button backButton;

    @FXML
    private TextField dateallowText;

    @FXML
    private TextField datedeclaimedText;

    @FXML
    private TextField locationText;

    @FXML
    private TextField nameText;

    //Окно добавления груза
    @FXML
    void initialize() {
        DatabaseHandler dbhandler = new DatabaseHandler();
        //Кнопка "Добавить"
        addButton.setOnAction(event -> {
            dbhandler.addCargo(nameText.getText(), dateallowText.getText(), datedeclaimedText.getText(),
                    locationText.getText());
            addButton.getScene().getWindow().hide();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(ControllerMain.class.getResource("MainApplication.fxml"));
                Parent root = fxmlLoader.load();
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setOpacity(1);
                stage.setTitle("Проверка грузов");
                stage.setScene(new Scene(root, 600, 486));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        //Кнопка "Назад"
        backButton.setOnAction(actionEvent -> {backButton.getScene().getWindow().hide();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(ControllerMain.class.getResource("MainApplication.fxml"));
                Parent root = fxmlLoader.load();
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setOpacity(1);
                stage.setTitle("Проверка грузов");
                stage.setScene(new Scene(root, 600, 486));
                ControllerMain.llist.add(String.valueOf(ControllerMain.identificator + 1));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }});
    }
}
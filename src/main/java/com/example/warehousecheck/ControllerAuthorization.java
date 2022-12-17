package com.example.warehousecheck;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControllerAuthorization {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private Text check;
    @FXML
    private Button loginbutton;

    @FXML
    public TextField logintext;

    @FXML
    public TextField passwordtext;

    @FXML
    void initialize() {
        loginbutton.setOnAction(event -> {
            if (logintext.getText().toString().equals("1") && passwordtext.getText().toString().equals("123")) {
                loginbutton.getScene().getWindow().hide();
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
            }
            else if(logintext.getText().isEmpty()&&passwordtext.getText().isEmpty()){
                check.setText("Пожалуйста заполните данные!");
            }
            else {check.setText("Неправильный логин или пароль!");}
        });
    }
}

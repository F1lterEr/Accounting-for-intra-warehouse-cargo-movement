package com.example.warehousecheck;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

//Главное окно
public class ControllerMain {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button editButton;

    @FXML
    private Button infoButton;
    public static int identificator = 0;
    public static ObservableList<String> llist = FXCollections.observableArrayList();
    public static int id = 0;

    @FXML
    public ListView<String> cargolist;
    ObservableList<String> list = FXCollections.observableArrayList();
    //Главное окно
    @FXML
    void initialize() throws Exception{
        //Метод отображения груза в списке
        toDisplay();
        //Кнопка "Информация о грузе"
        infoButton.setOnAction(actionEvent -> {infoButton.getScene().getWindow().hide();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(ControllerMain.class.getResource("CheckingInformation.fxml"));
                Parent root = fxmlLoader.load();
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setOpacity(1);
                stage.setTitle("Информация о грузе");
                stage.setScene(new Scene(root, 600, 486));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }});
        //Кнопка "Редактировать"
        editButton.setOnAction(actionEvent -> {editButton.getScene().getWindow().hide();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(ControllerMain.class.getResource("EditingInformation.fxml"));
                Parent root = fxmlLoader.load();
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setOpacity(1);
                stage.setTitle("Редактирование информации");
                stage.setScene(new Scene(root, 600, 486));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }});
        //Кнопка "Добавить"
        addButton.setOnAction(actionEvent -> {addButton.getScene().getWindow().hide();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(ControllerMain.class.getResource("AddInformation.fxml"));
                Parent root = fxmlLoader.load();
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setOpacity(1);
                stage.setTitle("Добавление груза");
                stage.setScene(new Scene(root, 600, 486));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }});
        //Кнопка "Удалить"
        deleteButton.setOnAction(actionEvent -> {
            try {
                toDelete(identificator);
                toDisplay();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    //Метод отображения груза в списке
    public void toDisplay() throws Exception {
        list.clear();
        cargolist.setItems(list);
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cargobd", "root","Iklipop321KLP");
        ResultSet rs = connection.createStatement().executeQuery("select * from cargo");
        llist.clear();
        while (rs.next()) {
            list.add(rs.getString(2));
            identificator=rs.getInt(1);
            llist.add(String.valueOf(identificator));
        }
        cargolist.setItems(list);
    }
    //Метод удаления груза из списка
    public void toDelete(int id) throws Exception{
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cargobd", "root","Iklipop321KLP");
        String sql = String.format("DELETE from cargo WHERE id='%s'", id);
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.execute();
        toDisplay();
    }
    //Метод выбора груза из списка
    public void getSelected(MouseEvent mouseEvent) {
        try{
            int index = cargolist.getSelectionModel().getSelectedIndex();
            if(index < -1){
                return;
            }
            else{
                id=index;
            }
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }
}

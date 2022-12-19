package com.example.warehousecheck;

import java.sql.*;
//Обработчик базы данных
public class DatabaseHandler extends Cargo {
    Connection dbConnection;
    public Connection getDbConnection() throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }
    public void addCargo(String Name, String DateAllow, String DateDeclaime, String Location) {
        try {
            String insert = "INSERT INTO " + Const.CARGO_TABLE + "(" + Const.CARGO_NAME +
                    "," + Const.CARGO_DATE_ALLOW + "," + Const.CARGO_DATE_DECLAIMED + "," + Const.CARGO_LOCATION + ")" + "VALUES(?,?,?,?)";
            dbConnection = getDbConnection();
            PreparedStatement prst = dbConnection.prepareStatement(insert);
            prst.setString(1, Name);
            prst.setString(2, DateAllow);
            prst.setString(3, DateDeclaime);
            prst.setString(4, Location);
            prst.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public ResultSet cargolist(){
        ResultSet resultSet = null;
        String select = "SELECT * FROM cargobd";
        try {
            PreparedStatement prst = getDbConnection().prepareStatement(select);
            resultSet = prst.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
}

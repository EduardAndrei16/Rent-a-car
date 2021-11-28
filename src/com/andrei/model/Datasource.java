package com.andrei.model;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Datasource {

    public static final String TABLE_CARS ="cars";
    public static final String COLUMN_CAR_ID = "idcars";
    public static final String COLUMN_CAR_BRAND ="brand";
    public static final String COLUMN_CAR_MODEL ="model";
    public static final String COLUMN_CAR_PRICE ="price";
    public static final String COLUMN_CAR_TYPE ="type";
    public static final String COLUMN_CAR_COLOR ="color";
    public static final int ORDER_BY_NONE = 1;
    public static final int ORDER_BY_ASC = 2;
    public static final int ORDER_BY_DESC =3;

    private Connection conn;
    Scanner keyboard = new Scanner(System.in);


    public boolean open() {
        try {
           conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/rent_car","andrei","andrei16");
            Statement stmt = conn.createStatement();

            String sql = "CREATE TABLE rent_car.cars ( " +
                            "idcars INT NOT NULL AUTO_INCREMENT, "+
                            "brand VARCHAR(200) NULL, "+
                            "model VARCHAR(200) NULL, "+
                            "price FLOAT NULL, "+
                            "type VARCHAR(45) NULL,"+
                            "color VARCHAR(50) NULL,"+
                            " PRIMARY KEY (idcars)); ";
            stmt.executeUpdate(sql);
            System.out.println("Created table....");
            return true;

        } catch(SQLException e){
            System.out.println("Couldn't connect to database: " +e.getMessage());
            return false;
        }
    }

    public void close(){
        try {
            if (conn != null) {
                conn.close();
            }


        }
            catch(SQLException e){
                System.out.println("Couldn't close connection: " + e);
            }
        }

    public List<Cars> queryCars(int sortOrder) {

            StringBuilder sb = new StringBuilder("SELECT * FROM ");
            sb.append(TABLE_CARS);
            if(sortOrder != ORDER_BY_NONE){
                sb.append(" ORDER BY ");
                sb.append(COLUMN_CAR_PRICE);

                if(sortOrder == ORDER_BY_DESC) {
                    sb.append(" DESC");
                }  else {
                            sb.append(" ASC");
                        }
                    }

            try(Statement statement = conn.createStatement();
                ResultSet results = statement.executeQuery(sb.toString())) {


                List<Cars> cars = new ArrayList<>();
                while(results.next()) {
                    Cars car = new Cars();
                    car.setIdcars(results.getInt(COLUMN_CAR_ID));
                    car.setBrand(results.getString(COLUMN_CAR_BRAND));
                    car.setColor(results.getString(COLUMN_CAR_COLOR));
                    car.setPrice(results.getFloat(COLUMN_CAR_PRICE));
                    car.setType(results.getString(COLUMN_CAR_TYPE));
                    car.setModel(results.getString(COLUMN_CAR_MODEL));
                    cars.add(car);
                }

                return cars;

            } catch (SQLException e){
                System.out.println("Query failed: " + e);
                return null;

            }
        }

    public List<Cars> queryCarsByType() {

        StringBuilder sb = new StringBuilder("SELECT ");
        sb.append(COLUMN_CAR_TYPE);
        sb.append((", COUNT(*) as a FROM "));
        sb.append(TABLE_CARS);
        sb.append(" GROUP BY ");
        sb.append(COLUMN_CAR_TYPE);

        try(Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery(sb.toString())) {


            List<Cars> cars = new ArrayList<>();
            while(results.next()) {
                Cars car = new Cars();
                car.setCount(results.getInt("a"));
                car.setType(results.getString(COLUMN_CAR_TYPE));
                cars.add(car);
            }

            return cars;

        } catch (SQLException e){
            System.out.println("Query failed: " + e);
            return null;
        }


}

    public void insertCar() throws SQLException {
        System.out.println("Name: ");
        String brand = keyboard.next();

      if(!InputValidator.ValidateStringLength(brand)) {
           return;
       }

        System.out.println("Model: ");
        String model = keyboard.next();
        System.out.println("Type: ");
        String type = keyboard.next();

      if(!InputValidator.ValidateStringType(type)){
           return ;
       }

        System.out.println("Price: ");
        float price = keyboard.nextFloat();

      if(!InputValidator.CheckPriceInput(type,price)) {
          return;
      }

        System.out.println("Color: ");
        String color = keyboard.next();
      if(!InputValidator.ColorInputCheck(color)){
          return;
      }

        String sql = "INSERT INTO " +TABLE_CARS+ " (brand, model, price, type, color) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, brand);
        statement.setString(2, model);
        statement.setFloat(3, price);
        statement.setString(4, type);
        statement.setString(5, color);

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new car was inserted successfully!");

    }
    }

    public void update() throws SQLException{
        System.out.println("ID: ");
    int id;
    id = keyboard.nextInt();
        System.out.println("New Price:  ");
    float price;
    price = keyboard.nextFloat();
    if(!InputValidator.CheckPriceInput("w/e",price)){
        return;
    }
        String query = "UPDATE `rent_car`.`cars` SET `price` = ? WHERE (`idcars` = ?);";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setFloat(1,price);
        statement.setInt(2,id);

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Price updated succsesfully!");

        }

    }

    public void clean()throws SQLException{
        String query = "DELETE FROM cars;";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.executeUpdate();
        System.out.println("Database was cleanead!");
    }
    }
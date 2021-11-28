package com.andrei;
import com.andrei.model.Cars;
import com.andrei.model.Datasource;
import java.sql.*;
import java.util.List;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws SQLException {
        Scanner keyboard = new Scanner(System.in);
        int optiune;
        Datasource datasource = new Datasource();
        if (!datasource.open()) {
            System.out.println("Can't open datasource");
        }

        do {
            System.out.println(" ");
            System.out.println("1.Add a car");
            System.out.println("2.Update");
            System.out.println("3.Sort cars by Price");
            System.out.println("4.All car types and the number of cars for each type");
            System.out.println("5.Clean database");
            System.out.println("6.Exit");
            System.out.println(" ");


            optiune = keyboard.nextInt();

            switch (optiune) {
                case 1:
                    datasource.insertCar();
                    break;

                case 2:
                    datasource.update();
                    break;

                case 3:
                    List<Cars> cars = datasource.queryCars(Datasource.ORDER_BY_DESC);
                    if (cars.isEmpty()) {
                        System.out.println("There are no cars in the database !");
                        break;
                    }

                    for (Cars a : cars) {
                        System.out.println("ID = " + a.getIdcars() + ", Brand = " + a.getBrand() + ", Price = " + a.getPrice() + " Model = " +a.getModel()+ " Type = " + a.getType() +" Color = " +a.getColor());
                    }
                    break;

                case 4:
                    List<Cars> b = datasource.queryCarsByType();
                    for (Cars a : b) {
                        System.out.println("Type:"+ a.getType() + " Count: " + a.getCount());
                    }
                    break;
                case 5:
                    datasource.clean();
                    break;

                case 6:
                    System.out.println("Database closed!");
                    datasource.close();
                    optiune = 2147483647;
                    break;
                default:
                    System.out.println("Invalid input");
                    optiune = 2147483647;
                    datasource.close();

            }



        } while(optiune <= 6);

    }
}

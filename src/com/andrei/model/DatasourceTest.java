package com.andrei.model;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DatasourceTest {
    Datasource test = new Datasource();

    @Test
    void open() {
    assertTrue(test.open());
    }

    @Test
    void close() {

        test.open();
        assertNotNull(test.queryCars(Datasource.ORDER_BY_NONE));
        test.close();
        assertNull(test.queryCars(Datasource.ORDER_BY_DESC));


    }

    @Test
    void queryCars() {

        List<Cars> testList = test.queryCars(2);



    }

    @Test
    void queryCarsByType() {


    }

    @Test
    void insertCar() {

    }

    @Test
    void update() {
    }

    @Test
    void clean() {
    }
}
package com.services;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class DBUtil {
    private static HikariDataSource dataSource;
    static int i = 0;

    public static HikariDataSource getDataSourcePool() {
        if (dataSource == null) {
            getDataSource();
        }
        return dataSource;
    }

    public static DataSource getDataSource() {
        try {
            dataSource = new HikariDataSource();
            dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/applestore");
            dataSource.setUsername("root");
            dataSource.setPassword("root");
            dataSource.setMaximumPoolSize(5);
            System.out.println("Connection created");
        } catch (Exception e) {
            while (i<2){
                i++;
                getDataSourcePool();
            }
        }
        return dataSource;
    }
}

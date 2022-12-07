package com.services;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class DBUtil {
    private  static HikariDataSource dataSource;

    static{
        try{
            dataSource = new HikariDataSource();
            dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/applestore");
            dataSource.setUsername("root");
            dataSource.setPassword("root");
            System.out.println("Connection created");
        }

        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public static DataSource getDataSource(){
        return dataSource;
    }
}

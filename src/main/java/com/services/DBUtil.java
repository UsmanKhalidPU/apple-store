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
        if(dataSource==null)
        {
            try {
                dataSource = new HikariDataSource();
                dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
                dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/applestore");
                dataSource.setUsername("root");
                dataSource.setPassword("root");
                dataSource.setMaximumPoolSize(5);
            } catch (Exception e) {
                System.out.println(e);
                getDataSourcePool();
            }
        }
        return dataSource;
    }
}

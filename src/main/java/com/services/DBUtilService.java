package com.services;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class DBUtilService {
    private static HikariDataSource dataSource;
    static int i = 0;

    public static HikariDataSource getDataSourcePool() {
        if (dataSource == null) {
            getDataSource();
        }
        return dataSource;
    }

    public static DataSource getDataSource() {
        if(dataSource==null) {
            try {
                dataSource = new HikariDataSource();
                dataSource.setDriverClassName(System.getenv("DriverClassName"));
                dataSource.setJdbcUrl(System.getenv("JdbcUrl"));
                dataSource.setUsername(System.getenv("Username"));
                dataSource.setPassword(System.getenv("Password"));
            } catch (Exception e) {
                while (i < 2) {
                    i++;
                    getDataSourcePool();
                }
            }
        }
        return dataSource;
    }
}

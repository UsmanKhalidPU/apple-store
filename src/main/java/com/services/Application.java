package com.services;

public class Application extends javax.ws.rs.core.Application {
    public Application ()
    {
        DBUtil.getDataSource();
    }
}

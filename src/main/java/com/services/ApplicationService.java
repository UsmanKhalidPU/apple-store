package com.services;

public class ApplicationService extends javax.ws.rs.core.Application {
    public ApplicationService()
    {
        DBUtil.getDataSource();
    }
}

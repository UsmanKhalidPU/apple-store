package com.services;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Base64;

@Provider
public class MyAuthService implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext ctx) throws IOException {

        String authStr = ctx.getHeaders().getFirst("Authorization");
        String[] authParts = authStr.split(" ");
        authStr = authParts[1];
        byte[] decoded = Base64.getDecoder().decode(authStr);
        String decodedStr = new String(decoded, StandardCharsets.UTF_8);
        authParts = decodedStr.split(":");

        Connection con = null;
        Statement stmt;

        try {
            con = DBUtilService.getDataSource().getConnection();
            stmt = con.createStatement();
            MessageDigest md=MessageDigest.getInstance("MD5");
            md.update(authParts[1].getBytes(),0,authParts[1].length());
            String SQL = "select * from users where users.name = '" + authParts[0] + "' AND users.password = '" + new BigInteger(1,md.digest()).toString(16) +"';" ;

            System.out.println(SQL);
            ResultSet rs = stmt.executeQuery(SQL);

            if(!rs.next()){
                ctx.abortWith(Response
                        .status(Response.Status.ACCEPTED)
                        .entity("User not authenticated")
                        .build());
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}


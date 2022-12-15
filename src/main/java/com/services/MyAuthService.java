package com.services;

import com.resources.MyAuth;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import java.io.IOException;


@Provider
public class MyAuthService implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext ctx) throws IOException {

        String str = ctx.getHeaders().getFirst("Authorization");
        if (!str.equals("Basic YWRtaW46cm9vdA==")) {

            ctx.abortWith(Response
                    .status(Response.Status.ACCEPTED)
                    .entity("Not Authenticated")
                    .build());
        }
//        if (ctx.getHeaders().getFirst("Authorization").equals("Basic YWRtaW46cm9vdA"))
//        {
//
//            ctx.abortWith(Response.status(Response.Status.FORBIDDEN)
//                    .entity("Cannot access")
//                    .build());
//        }

    }
}


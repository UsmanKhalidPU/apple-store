package com.services;

import javax.interceptor.Interceptor;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Interceptor
@Provider
@PreMatching
public class MyAuthService implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext ctx) throws IOException {
        if (ctx.getHeaders().getFirst("Authorization").equals("Basic YWRtaW46cm9vdA"))
        {

            ctx.abortWith(Response.status(Response.Status.FORBIDDEN)
                    .entity("Cannot access")
                    .build());
        }

    }
}

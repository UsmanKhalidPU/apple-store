package com.services;

import com.domain.MyAuth;

import javax.interceptor.Interceptor;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.logging.Filter;
import java.util.logging.LogRecord;

@Interceptor
@Provider
@MyAuth
public class MyAuthInterceptorService implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext ctx) throws IOException {
        if (ctx.getHeaders()) != null && "EN".equals(ctx.getLanguage()
                .getLanguage())) {

            ctx.abortWith(Response.status(Response.Status.FORBIDDEN)
                    .entity("Cannot access")
                    .build());
        }

    }
}

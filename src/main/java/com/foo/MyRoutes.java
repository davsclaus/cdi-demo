package com.foo;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;
import org.apache.camel.model.rest.RestParamType;

@ContextName("myCamel")
public class MyRoutes extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        // configure we want to use servlet as the component for the rest DSL
        // and we enable json binding mode

        restConfiguration()
            .component("netty4-http").port(8080).host("localhost")
            .contextPath("/")
            .apiContextPath("api-doc")
            .enableCORS(true);

        rest()
            .consumes("plain/text").produces("plain/text")

            .get("/ping")
                .route().transform().constant("pong")
            .endRest()

            .get("/hello/{name}")
                .param().type(RestParamType.query).name("name").description("Name of person").endParam()
                .route().transform().simple("Hello ${header.name} I am ${sys.HOSTNAME}")
            .endRest();
    }

}

package com.reacitvespring.router;

import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

public class ReviewRouter {

  public RouterFunction<ServerResponse> reviewRoute() {
    return RouterFunctions.route().GET("/v1/helloworld", request -> ServerResponse.ok().bodyValue("Hello World"))
        .build();
  }
}

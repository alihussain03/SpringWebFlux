package com.reacitvespring.controller;

import java.time.Duration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class FluxAndMonoController {

  @GetMapping("/flux")
  public Flux<Integer> flux() {
    return Flux.just(1, 2, 3).log();
  }

  @GetMapping("/mono")
  public Mono<String> mono() {
    return Mono.just("Hello World!").log();
  }

  @GetMapping("/stream")
  public Flux<Long> stream() {
    return Flux.interval(Duration.ofSeconds(1)).log();
  }
}

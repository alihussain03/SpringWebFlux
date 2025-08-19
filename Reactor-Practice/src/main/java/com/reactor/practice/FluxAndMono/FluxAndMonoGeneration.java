package com.reactor.practice.FluxAndMono;

import java.util.List;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FluxAndMonoGeneration {

  Flux<String> getFlex() {
    return Flux.fromIterable(List.of("FirstElement", "SecondElement", "ThirdElement")).map(String::toUpperCase).log();
  }

  Mono<String> getMono() {
    return Mono.just("Mono").log();
  }

  public static void main(String[] args) {
    FluxAndMonoGeneration fluxAndMonoGeneration = new FluxAndMonoGeneration();
    fluxAndMonoGeneration.getFlex().subscribe(s -> System.out.println("From Flex: " + s));
    fluxAndMonoGeneration.getMono().subscribe(s -> System.out.println("From Mono: " + s));
  }
}

package com.reacitvespring.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.test.StepVerifier;

@WebFluxTest(controllers = FluxAndMonoController.class)
@AutoConfigureWebTestClient
class FluxAndMonoControllerTest {

  @Autowired
  WebTestClient webTestClient;

  @Test
  void flux() {
    webTestClient.get().uri("/flux").exchange().expectStatus().is2xxSuccessful().expectBodyList(Integer.class)
        .hasSize(3);
  }

  @Test
  void flux_approach2() {
    var flux = webTestClient.get().uri("/flux").exchange().expectStatus().is2xxSuccessful().returnResult(Integer.class)
        .getResponseBody();
    StepVerifier.create(flux).expectNext(1, 2, 3).verifyComplete();
  }

  @Test
  void flux_approach3() {
    webTestClient.get().uri("/flux").exchange().expectStatus().is2xxSuccessful().expectBodyList(Integer.class)
        .consumeWith(integerFluxExchangeResult -> {
          var res = integerFluxExchangeResult.getResponseBody();
          assert (res.size() == 3);
        });
  }

  @Test
  void Mono_approach3() {
    webTestClient.get().uri("/mono").exchange().expectStatus().is2xxSuccessful().expectBody(String.class)
        .consumeWith(integerFluxExchangeResult -> {
          var res = integerFluxExchangeResult.getResponseBody();
          assertEquals("Hello World!", res);
        });
  }

  @Test
  void flux_approach4() {
    var flux = webTestClient.get().uri("/stream").exchange().expectStatus().is2xxSuccessful().returnResult(Long.class)
        .getResponseBody();
    StepVerifier.create(flux).expectNext(1L, 2L, 3L).thenCancel();
  }
}
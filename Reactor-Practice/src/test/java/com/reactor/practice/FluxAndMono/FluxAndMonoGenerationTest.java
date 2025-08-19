package com.reactor.practice.FluxAndMono;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

class FluxAndMonoGenerationTest {

  FluxAndMonoGeneration fluxAndMonoGeneration = new FluxAndMonoGeneration();

  @Test
  void namesFlux() {
    // given
    // when
    var flex = fluxAndMonoGeneration.getFlex();
    // then
    StepVerifier.create(flex).expectNextCount(3).expectComplete().verify();
  }

  @Test
  void upperCaseNamesFlux() {
    var flex = fluxAndMonoGeneration.getFlex();
    StepVerifier.create(flex).expectNext("FIRSTELEMENT", "SECONDELEMENT", "THIRDELEMENT").expectComplete().verify();
  }
}
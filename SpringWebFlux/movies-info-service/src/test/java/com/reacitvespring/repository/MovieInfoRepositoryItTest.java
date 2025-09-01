package com.reacitvespring.repository;

import com.reacitvespring.domain.MovieInfo;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;
import reactor.test.StepVerifier;

@DataMongoTest
@ActiveProfiles("test")
class MovieInfoRepositoryItTest {

  @Autowired
  MovieInfoRepository movieInfoRepository;

  @BeforeEach
  void setup() {
    var movie = List.of(new MovieInfo("abc", "Movie1", 2005, List.of("Ch1", "ch2"), LocalDate.parse("2025-01-01")));
    movieInfoRepository.saveAll(movie).blockLast();
  }

  @Test
  void findAll() {
    //when
    var movies = movieInfoRepository.findAll();
    //then
    StepVerifier.create(movies).expectNextCount(1).verifyComplete();
  }

  @Test
  void findById() {
    //when
    var moviesInfoMono = movieInfoRepository.findById("abc");
    //then
    StepVerifier.create(moviesInfoMono).assertNext(movieInfo -> {
      Assertions.assertEquals("Movie1", movieInfo.getName());
    }).verifyComplete();
  }

  @Test
  void saveMovieInfo() {
    //when
    var movieInfoEntity = new MovieInfo("xyz", "Movie2", 2005, List.of("Ch1", "ch2"), LocalDate.parse("2025-01-01"));
    var moviesInfoMono = movieInfoRepository.save(movieInfoEntity);
    //then
    StepVerifier.create(moviesInfoMono).assertNext(movieInfo -> {
      Assertions.assertNotNull(movieInfo.getMovieInfoId());
      Assertions.assertEquals("Movie2", movieInfo.getName());
    }).verifyComplete();
  }

  @Test
  void updateMovieInfo() {
    //when
    var movieInfoEntity = movieInfoRepository.findById("abc").block();
    movieInfoEntity.setYear(2022);

    var moviesInfoMono = movieInfoRepository.save(movieInfoEntity);
    //then
    StepVerifier.create(moviesInfoMono).assertNext(movieInfo -> {
      Assertions.assertNotNull(movieInfo.getMovieInfoId());
      Assertions.assertEquals(2022, movieInfo.getYear());
    }).verifyComplete();
  }

  @Test
  void deleteMovieInfo() {
    //when
    movieInfoRepository.deleteById("abc").block();
    var movies = movieInfoRepository.findAll();
    //then
    StepVerifier.create(movies).expectNextCount(0).verifyComplete();
  }
}
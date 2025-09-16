package com.reacitvespring.repository;

import com.reacitvespring.domain.Review;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ReviewReactiveRepository extends ReactiveMongoRepository<Review, String> {

  Flux<Review> findReviewsByMovieInfoId(Long movieInfoId);
}

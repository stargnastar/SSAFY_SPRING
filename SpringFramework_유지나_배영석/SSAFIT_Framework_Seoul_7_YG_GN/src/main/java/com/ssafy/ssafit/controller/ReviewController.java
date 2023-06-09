package com.ssafy.ssafit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.model.dto.Article;
import com.ssafy.ssafit.model.dto.Review;
import com.ssafy.ssafit.model.dto.SearchCondition;
import com.ssafy.ssafit.model.service.ArticleService;
import com.ssafy.ssafit.model.service.ReviewService;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api-review")
@Api(tags = "리뷰 컨트롤러")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;

	@GetMapping("/review/{reviewId}")
	public ResponseEntity<Review> articleDetail(@PathVariable int reviewId) {
		Review review = reviewService.getReview(reviewId);
		return new ResponseEntity<Review>(review, HttpStatus.OK);
	}

	@PostMapping("/review/{articleId}")
	public ResponseEntity<Review> writeReview(@PathVariable int articleId, Review review) {
		review.setArticleId(articleId);
		reviewService.writeReview(review);
		return new ResponseEntity<Review>(review, HttpStatus.CREATED);
	}

	@PutMapping("/review/{reviewId}")
	public ResponseEntity<Void> modifyArticle(@PathVariable int reviewId, @RequestBody Review review) {
		review.setId(reviewId);
		reviewService.modifyReview(review);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@DeleteMapping("/review/{reviewId}")
	public ResponseEntity<Void> deleteAritlce(@PathVariable int reviewId) {
		reviewService.removeReview(reviewId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@GetMapping("/article/search")
	public ResponseEntity<?> searchReview(SearchCondition searchCondition) {
		List<Review> reviewList = reviewService.searchReview(searchCondition);

		if (reviewList == null || reviewList.size() == 0) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Review>>(reviewList, HttpStatus.OK);
	}

}

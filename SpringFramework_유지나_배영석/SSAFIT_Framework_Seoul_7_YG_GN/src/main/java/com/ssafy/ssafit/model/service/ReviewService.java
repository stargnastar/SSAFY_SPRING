package com.ssafy.ssafit.model.service;

import java.util.List;

import com.ssafy.ssafit.model.dto.Review;
import com.ssafy.ssafit.model.dto.SearchCondition;

public interface ReviewService {

	// R
	public List<Review> getReviewList(int articleId);
	
	public Review getReview(int reviewId);
	
	public List<Review> searchReview(SearchCondition searchCondition);
	
	// C
	public int writeReview(Review review);
	
	// U
	public int modifyReview(Review review);
	
	// D
	public int removeReview(int reviewId);
	
	
}

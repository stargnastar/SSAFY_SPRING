package com.ssafy.ssafit.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafy.ssafit.model.dto.Review;
import com.ssafy.ssafit.model.dto.SearchCondition;

@Repository
public interface ReviewDao {
	// c
	int insertReview(Review review);
	
	// r
	List<Review> selectAll(int articleId);
	
	Review selectOne(int reivewId);
	
	List<Review> searchReview(SearchCondition searchCondition);
	
	// u
	int updateReview(Review review);
	
	// d
	int deleteReview(int reviewId);
}

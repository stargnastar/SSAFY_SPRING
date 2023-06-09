package com.ssafy.ssafit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dao.ReviewDao;
import com.ssafy.ssafit.model.dto.Review;
import com.ssafy.ssafit.model.dto.SearchCondition;

@Service
public class ReviewServiceImpl implements ReviewService {

	private ReviewDao reviewDao;

	@Autowired
	public ReviewServiceImpl(ReviewDao reviewDao) {
		this.reviewDao = reviewDao;
	}

	@Override
	public List<Review> getReviewList(int articleId) {
		return reviewDao.selectAll(articleId);
	}

	@Override
	public Review getReview(int reviewId) {
		return reviewDao.selectOne(reviewId);
	}

	@Override
	public int writeReview(Review review) {
		return reviewDao.insertReview(review);
	}

	@Override
	public int modifyReview(Review review) {
		return reviewDao.updateReview(review);
	}

	@Override
	public int removeReview(int reviewId) {
		return reviewDao.deleteReview(reviewId);
	}

	@Override
	public List<Review> searchReview(SearchCondition searchCondition) {
		return reviewDao.searchReview(searchCondition);
	}

}

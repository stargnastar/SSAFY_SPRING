package com.ssafy.ssafit.model.dto;

import java.util.List;

public class ArticleResponse {
	private List<Review> reviewList;
	private Article article;
	
	public ArticleResponse() {

	}

	public List<Review> getReviewList() {
		return reviewList;
	}

	public void setReviewList(List<Review> reviewList) {
		this.reviewList = reviewList;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}


	
	
}

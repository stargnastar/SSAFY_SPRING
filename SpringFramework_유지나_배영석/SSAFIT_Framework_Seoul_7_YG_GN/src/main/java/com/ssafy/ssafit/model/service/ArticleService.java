package com.ssafy.ssafit.model.service;

import java.util.List;

import com.ssafy.ssafit.model.dto.Article;
import com.ssafy.ssafit.model.dto.SearchCondition;

public interface ArticleService {
	
	// R
	public List<Article> getArticleList();
	
	public Article getArticle(int ArticleId);
	
	public List<Article> searchArticle(SearchCondition search);
	
	// C
	public int writeArticle(Article article);
	
	// U
	public int modifyArticle(Article article);
	
	// D
	public int removeArticle(int ArticleId);
	

}

package com.ssafy.ssafit.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafy.ssafit.model.dto.Article;
import com.ssafy.ssafit.model.dto.SearchCondition;

@Repository
public interface ArticleDao {
	// R
	List<Article> selectAll();
	
	Article selectOne(int articleId);
	
	// 검색 기능
	List<Article> searchArticle(SearchCondition searchCondition);
	
	// C
	int insertArticle(Article article);
	
	// U
	int updateArticle(Article article);
	
	int updateViewCnt(int articleId);
	
	// D
	int deleteAritcle(int articleId);
	
	
}

package com.ssafy.ssafit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dao.ArticleDao;
import com.ssafy.ssafit.model.dto.Article;
import com.ssafy.ssafit.model.dto.SearchCondition;

@Service
public class ArticleServiceImpl implements ArticleService {
	
	private ArticleDao articleDao;
	
	@Autowired
	public ArticleServiceImpl(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}

	@Override
	public List<Article> getArticleList() {
		return articleDao.selectAll();
	}

	@Override
	public Article getArticle(int articleId) {
		return articleDao.selectOne(articleId);
	}

	@Override
	public int writeArticle(Article article) {
		return articleDao.insertArticle(article);
	}

	@Override
	public int modifyArticle(Article article) {
		return articleDao.updateArticle(article);
	}

	@Override
	public int removeArticle(int articleId) {
		return articleDao.deleteAritcle(articleId);
	}

	@Override
	public List<Article> searchArticle(SearchCondition searchCondition) {
		return articleDao.searchArticle(searchCondition);
	}

}

package com.ssafy.ssafit.controller;

import java.util.List;

import javax.websocket.server.PathParam;

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

import com.ssafy.ssafit.model.dao.ArticleDao;
import com.ssafy.ssafit.model.dto.Article;
import com.ssafy.ssafit.model.dto.ArticleResponse;
import com.ssafy.ssafit.model.dto.Review;
import com.ssafy.ssafit.model.dto.SearchCondition;
import com.ssafy.ssafit.model.service.ArticleService;
import com.ssafy.ssafit.model.service.ReviewService;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api-article")
@Api(tags = "article 컨트롤러")
public class ArticleController {

	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private ReviewService reviewService;

	@GetMapping("/article/list")
	public ResponseEntity<?> getArticleList() {
		List<Article> articleList = articleService.getArticleList();

		if (articleList == null || articleList.size() == 0) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Article>>(articleList, HttpStatus.OK);
	}

	@GetMapping("/article/{id}")
	public ResponseEntity<ArticleResponse> articleDetail(@PathVariable int id) {
		Article article = articleService.getArticle(id);
		List<Review> review = reviewService.getReviewList(id);
		ArticleResponse articleResponse = new ArticleResponse();
		articleResponse.setArticle(article);
		articleResponse.setReviewList(review);
		return new ResponseEntity<ArticleResponse>(articleResponse, HttpStatus.OK);
	}

	@PostMapping("/article")
	public ResponseEntity<Article> writeArticle(Article article) {
		articleService.writeArticle(article);
		return new ResponseEntity<Article>(article, HttpStatus.CREATED);
	}

	@PutMapping("/article")
	public ResponseEntity<Void> modifyArticle(@RequestBody Article article) {
		articleService.modifyArticle(article);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@DeleteMapping("/article/{id}")
	public ResponseEntity<Void> deleteAritlce(int id) {
		articleService.removeArticle(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@GetMapping("/article/search")
	public ResponseEntity<?> searchArticle(SearchCondition searchCondition) {
		List<Article> articleList = articleService.searchArticle(searchCondition);

		if (articleList == null || articleList.size() == 0) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Article>>(articleList, HttpStatus.OK);
	}

}

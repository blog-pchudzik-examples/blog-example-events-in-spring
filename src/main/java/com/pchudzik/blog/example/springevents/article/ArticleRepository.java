package com.pchudzik.blog.example.springevents.article;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
class ArticleRepository {
	private final Map<String, Article> articleDb = new HashMap<>();

	public Article load(String id) {
		return Optional
				.ofNullable(articleDb.get(id))
				.orElseThrow(() -> new NoSuchElementException("No article with id " + id));
	}

	public void save(Article article) {
		if(ArticleService.EXPECTED_TO_FAIL.equals(article.getArticleId())) {
			throw new RuntimeException("Article save failure");
		}

		articleDb.put(article.getArticleId(), article);
	}
}

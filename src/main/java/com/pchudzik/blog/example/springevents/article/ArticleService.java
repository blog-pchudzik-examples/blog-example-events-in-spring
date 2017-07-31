package com.pchudzik.blog.example.springevents.article;

import com.pchudzik.blog.example.springevents.article.event.ArticleCreatedEvent;
import com.pchudzik.blog.example.springevents.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ArticleService {
	public static final String EXPECTED_TO_FAIL = "expectedToFail";

	private final ArticleEventDispatcher eventDispatcher;
	private final ArticleRepository articleRepository;

	@Transactional
	public Article createArticle(String articleId, User loggedUser, String content) {
		final Article result = new Article(articleId, loggedUser, content);
		try {
			articleRepository.save(result);
			return result;
		} finally {
			eventDispatcher.dispatch(new ArticleCreatedEvent(result));
		}
	}

	@Transactional
	public void updateArticle(String articleId, Article.UpdateType updateType, User loggedUser, String updatedContent) {
		final Article article = articleRepository.load(articleId);
		article
				.updateContent(updateType, loggedUser, updatedContent)
				.forEach(eventDispatcher::dispatch);
	}
}

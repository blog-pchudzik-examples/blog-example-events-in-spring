package com.pchudzik.blog.example.springevents.article.notification;

import com.pchudzik.blog.example.springevents.article.event.ArticleCreatedEvent;
import com.pchudzik.blog.example.springevents.article.event.ArticleUpdateEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Service
class PublishedArticleNotifier {
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void onArticleCreated(ArticleCreatedEvent event) {
		log.info("Publishing article {} to facebook", event.getArticle());
	}

	@TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
	public void articleSaveFailure(ArticleCreatedEvent event) {
		log.info("Publishing article {} save failure to backup storage", event.getArticle());
	}

	@EventListener(condition = "#event.updateType == T(com.pchudzik.blog.example.springevents.article.Article.UpdateType).MAJOR")
	public void onArticleUpdate(ArticleUpdateEvent event) {
		log.info("Sending notification to the author about major article update {}", event.getArticle());
	}
}

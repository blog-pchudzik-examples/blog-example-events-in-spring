package com.pchudzik.blog.example.springevents.article;

import com.pchudzik.blog.example.springevents.article.event.ArticleEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class ArticleEventDispatcher {
	private final ApplicationEventPublisher eventPublisher;

	public void dispatch(ArticleEvent articleEvent) {
		eventPublisher.publishEvent(articleEvent);
	}
}

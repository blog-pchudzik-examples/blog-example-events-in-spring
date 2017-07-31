package com.pchudzik.blog.example.springevents.article.event;

import com.pchudzik.blog.example.springevents.article.Article;
import com.pchudzik.blog.example.springevents.article.Article.ArticleUpdate;
import com.pchudzik.blog.example.springevents.article.Article.UpdateType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ArticleUpdateEvent implements ArticleEvent {
	private final Article article;
	private final ArticleUpdate articleUpdate;

	public UpdateType getUpdateType() {
		return articleUpdate.getUpdateType();
	}
}

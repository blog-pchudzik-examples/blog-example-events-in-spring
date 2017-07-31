package com.pchudzik.blog.example.springevents.article.event;

import com.pchudzik.blog.example.springevents.article.Article;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ArticleCreatedEvent implements ArticleEvent {
	private final Article article;
}

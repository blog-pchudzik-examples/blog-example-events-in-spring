package com.pchudzik.blog.example.springevents.article;

import com.pchudzik.blog.example.springevents.article.event.ArticleEvent;
import com.pchudzik.blog.example.springevents.article.event.ArticleUpdateEvent;
import com.pchudzik.blog.example.springevents.user.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.LinkedList;
import java.util.List;

import static java.util.Collections.singletonList;

@ToString(of = {"articleId"})
public class Article {
	@Getter
	private final String articleId;
	private String content;
	private User author;

	private List<ArticleUpdate> history = new LinkedList<>();

	public Article(String articleId, User author, String content) {
		this.articleId = articleId;
		this.author = author;
		this.content = content;
	}

	public List<ArticleEvent> updateContent(UpdateType updateType, User author, String newContent) {
		final ArticleUpdate update = new ArticleUpdate(
				this.content,
				newContent,
				updateType,
				author);
		history.add(update);
		this.content = newContent;
		return singletonList(new ArticleUpdateEvent(this, update));
	}

	public enum UpdateType {
		TYPO,
		MINOR,
		MAJOR
	}

	@RequiredArgsConstructor
	public static class ArticleUpdate {
		private final String oldContent;
		private final String newContent;

		@Getter
		private final UpdateType updateType;

		private final User author;
	}
}

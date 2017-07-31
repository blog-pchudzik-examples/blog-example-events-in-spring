package com.pchudzik.blog.example.springevents;

import com.pchudzik.blog.example.springevents.article.Article;
import com.pchudzik.blog.example.springevents.article.ArticleService;
import com.pchudzik.blog.example.springevents.label.Category;
import com.pchudzik.blog.example.springevents.label.LabelService;
import com.pchudzik.blog.example.springevents.label.Tag;
import com.pchudzik.blog.example.springevents.user.Permission;
import com.pchudzik.blog.example.springevents.user.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import static com.pchudzik.blog.example.springevents.article.ArticleService.EXPECTED_TO_FAIL;

@EnableAsync
@EnableTransactionManagement
@SpringBootApplication
public class SpringEventsApplication {
	public static void main(String[] args) {
		final ApplicationContext applicationContext = SpringApplication.run(SpringEventsApplication.class, args);

		final UserService userService = applicationContext.getBean(UserService.class);
		final ArticleService articleService = applicationContext.getBean(ArticleService.class);
		final LabelService labelService = applicationContext.getBean(LabelService.class);

		final String moderator = "moderator";
		userService.registerUser(moderator, "Moderator Moderator");
		userService.requestPermission(moderator, Permission.ADMIN);
		userService.requestPermission(moderator, Permission.MODERATOR);
		userService.rejectPermission(moderator, Permission.MODERATOR);
		userService.assignPermission(moderator, Permission.ADMIN);

		final Category category1 = labelService.createCategory("category");
		final Category category2 = labelService.createCategory("other category");
		final Tag tag1 = labelService.createTag("tag");
		final Tag tag2 = labelService.createTag("other tag");

		final String articleId = "valid article";
		final Article validArticle = articleService.createArticle(articleId, userService.loadUser(moderator), "any c_ntent");
		articleService.updateArticle(articleId, Article.UpdateType.TYPO, userService.loadUser(moderator), "any content");
		articleService.updateArticle(articleId, Article.UpdateType.MAJOR, userService.loadUser(moderator), "new content");

		try {
			final Article invalidArticle = articleService.createArticle(EXPECTED_TO_FAIL, userService.loadUser(moderator), "invalid content");
		} catch (Exception ex) {
			System.out.println("Can not save article " + EXPECTED_TO_FAIL);
		}
	}
}

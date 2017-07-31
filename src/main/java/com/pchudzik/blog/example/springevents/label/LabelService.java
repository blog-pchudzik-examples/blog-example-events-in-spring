package com.pchudzik.blog.example.springevents.label;

import com.pchudzik.blog.example.springevents.label.event.LabelCreated;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LabelService {
	private final ApplicationEventPublisher eventPublisher;

	public Category createCategory(String name) {
		final Category category = new Category(name);
		eventPublisher.publishEvent(new LabelCreated<>(category));
		return category;
	}

	public Tag createTag(String name) {
		final Tag tag = new Tag(name);
		eventPublisher.publishEvent(new LabelCreated<>(tag));
		return tag;
	}
}

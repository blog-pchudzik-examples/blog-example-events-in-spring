package com.pchudzik.blog.example.springevents.label.notification;

import com.pchudzik.blog.example.springevents.label.Category;
import com.pchudzik.blog.example.springevents.label.event.LabelCreated;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
class LabelNotificationService {
	@EventListener
	public void onCategoryCreation(LabelCreated<Category> event) {
		log.info("Category created {}. Sending for acceptance", event.getLabel());
	}
}

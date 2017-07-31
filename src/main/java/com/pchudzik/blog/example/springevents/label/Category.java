package com.pchudzik.blog.example.springevents.label;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Category implements Label {
	private final String name;
}

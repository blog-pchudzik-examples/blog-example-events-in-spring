package com.pchudzik.blog.example.springevents.label.event;

import com.pchudzik.blog.example.springevents.label.Label;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ResolvableType;
import org.springframework.core.ResolvableTypeProvider;

@Getter
@RequiredArgsConstructor
public class LabelCreated<T extends Label> implements ResolvableTypeProvider {
	private final T label;

	@Override
	public ResolvableType getResolvableType() {
		return ResolvableType.forClassWithGenerics(getClass(), label.getClass());
	}
}

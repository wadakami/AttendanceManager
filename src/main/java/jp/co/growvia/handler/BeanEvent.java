package jp.co.growvia.handler;

import org.springframework.context.ApplicationEvent;

public class BeanEvent extends ApplicationEvent {

	public BeanEvent(Object source) {
		super(source);

		System.out.println("Create MyBeanEvent");

	}

}

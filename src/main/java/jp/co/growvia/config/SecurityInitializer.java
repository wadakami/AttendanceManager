package jp.co.growvia.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer  {

	public SecurityInitializer() {
		super(SecurityInitializer.class);
	}

	@Override
	protected boolean enableHttpSessionEventPublisher() {
		System.out.println("EventPublisher");
		return true;
	}
}

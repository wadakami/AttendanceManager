package jp.co.growvia.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import jp.co.growvia.handler.LoginFailerHandler;
import jp.co.growvia.handler.LoginSuccessHandler;
import jp.co.growvia.handler.LogoutSuccessHandler;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/image/**", "/css/**", "/js/**","/webjars/**");
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests()
			.mvcMatchers("/login").permitAll()
			.anyRequest().authenticated()
			.and()
		.formLogin()
			.loginProcessingUrl("/top")
			.loginPage("/login")
			.usernameParameter("username")
			.passwordParameter("password")
			.successHandler(sccessHandler())
			.failureHandler(failerHandler())
			.permitAll()
			.and()
		.logout()
			.logoutUrl("/logout")
			.logoutSuccessHandler(logoutSuccess())
			.permitAll()
			.and()
		.sessionManagement()
			.maximumSessions(1)
			.maxSessionsPreventsLogin(true)
			.expiredSessionStrategy(new SessionManagement())
		;

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.inMemoryAuthentication()
		.withUser("user")
			.password("{noop}password")
			.roles("USER");
	}

	public LoginSuccessHandler sccessHandler() {
		return new LoginSuccessHandler();

	}

	public LoginFailerHandler failerHandler() {
		return new LoginFailerHandler();
	}

	public LogoutSuccessHandler logoutSuccess() {
		return new LogoutSuccessHandler();
	}
}

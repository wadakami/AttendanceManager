package jp.co.growvia.handler;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {


	Logger logger = LoggerFactory.getLogger(LoginSuccessHandler.class);

	private RequestCache requestCache = new HttpSessionRequestCache();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {


		SavedRequest savedRequest = requestCache.getRequest(request, response);
		String targetUrl = null;
		if(Objects.nonNull(savedRequest)) {
			targetUrl = savedRequest.getRedirectUrl();
        }

		logger.info("TargetUrl:" + targetUrl);
		HttpSession session = request.getSession(false);
		logger.info("SessionID:" + session.getId());
        if (Objects.nonNull(session)) {

    		if (Objects.nonNull(targetUrl)) {
            	if(targetUrl.contains("/admit")) {
            		response.sendRedirect(targetUrl);
        		}
            }
    		response.sendRedirect("/top");
        }
	}

}

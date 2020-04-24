package jp.co.growvia.handler;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;

public class LogoutSuccessHandler
		implements org.springframework.security.web.authentication.logout.LogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        if (Objects.nonNull(session)) {

            System.out.println("success loguot");
            System.out.println(session.getId());
            session.invalidate();
            System.out.println(session.getId());

    		response.sendRedirect("/login?logout");
        }
	}

}

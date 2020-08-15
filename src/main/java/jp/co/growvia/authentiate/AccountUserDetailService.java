package jp.co.growvia.authentiate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jp.co.growvia.entity.UserAccount;
import jp.co.growvia.repository.UserAccountRepository;

@Service
public class AccountUserDetailService implements UserDetailsService {

	@Autowired
	private UserAccountRepository userAccountRepository;

	Logger logger = LoggerFactory.getLogger(AccountUserDetailService.class);

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserAccount us = new UserAccount();
		us = userAccountRepository.findByUsername(username);

		logger.info("Login User:" + us.getUsername());

		if (Objects.isNull(us)) {
			return null;
		}
		// パスワードエンコーダー
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        GrantedAuthority authority = new SimpleGrantedAuthority("USER");
        grantList.add(authority);

        LoginUser userDetails = new LoginUser(us, grantList);

		return userDetails;
	}

}

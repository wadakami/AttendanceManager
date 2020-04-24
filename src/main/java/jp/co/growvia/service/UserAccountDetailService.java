package jp.co.growvia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jp.co.growvia.entity.UserAccount;
import jp.co.growvia.repository.UserAccountRepository;


@Service
public class UserAccountDetailService implements UserDetailsService {

	@Autowired
	private UserAccountRepository accountRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserAccount account = accountRepository.findByUsername(username);
		return account;
	}

}

package jp.co.growvia.authentiate;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import jp.co.growvia.entity.UserAccount;

public class LoginUser extends User {

	private UserAccount user;

	//String username, String password, Collection<? extends GrantedAuthority> authorities
	public LoginUser(UserAccount user, Collection<? extends GrantedAuthority> authorities) {
		super(user.getUsername(), user.getPassword(), authorities);
		this.user = user;
	}

	public UserAccount getUserAccount() {
		return this.user;
	}

	public int getAuthority() {
		return this.user.getAuthority();
	}

	public void setTempDate(LocalDate tempDate) {
		this.user.setTempdate(tempDate);
	}

	public LocalDate getTimeDate() {
		return this.user.getTempdate();
	}

}

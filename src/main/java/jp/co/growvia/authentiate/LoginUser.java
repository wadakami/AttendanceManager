package jp.co.growvia.authentiate;

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

	public String getUserId() {
		return this.user.getUserid();
	}

	public int getAuthority() {
		return this.user.getAuthority();
	}

	public void setTempDate(String tempDate) {
		this.user.setTempdate(tempDate);
	}

	public String getTempDate() {
		return this.user.getTempdate();
	}

	public String getEmail() {
		return this.user.getEmail();
	}

}

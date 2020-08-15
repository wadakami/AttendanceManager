package jp.co.growvia.entity;

import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
@Entity
@Table(name = "useraccount")
public class UserAccount implements UserDetails {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(nullable = true, unique = true)
	private String userid;

	@Column(nullable = true)
	private String username;

	@Column(nullable = true)
	private String password;

	@Column(nullable = true)
	private String kanjiname;

	@Column(nullable = true)
	private String hiraname;

	@Column(nullable = true)
	private LocalDate hiredate;

	@Column(nullable = true)
	private int authority;

	@Column(nullable = true)
	private boolean enabled;

	@Column(nullable = false)
	private String tempdate;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String authorizer;

	@Column(nullable = false)
	private Integer projectid;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String id) {
		this.userid = id;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setAuthority(int authority) {
		this.authority = authority;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}

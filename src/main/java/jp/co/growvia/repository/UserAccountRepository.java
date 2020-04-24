package jp.co.growvia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.growvia.entity.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
	public UserAccount findByUsername(String username);
}
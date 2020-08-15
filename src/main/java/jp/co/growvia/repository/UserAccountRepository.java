package jp.co.growvia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.growvia.entity.UserAccount;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
	public UserAccount findByUsername(String username);

	public UserAccount findByUserid(String userid);

	public UserAccount findByKanjiname(String kanjiname);
}
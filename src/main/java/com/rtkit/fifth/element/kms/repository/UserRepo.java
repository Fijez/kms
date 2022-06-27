package com.rtkit.fifth.element.kms.repository;

import com.rtkit.fifth.element.kms.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface UserRepo extends JpaRepository<User, Long> {

    User findByEmail(String email);
}

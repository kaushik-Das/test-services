package com.dadamohit.userservice.repositories;

import com.dadamohit.userservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  @Modifying
  @Query("update users u set u.medias = array_append(u.medias, ?2) where u.id = ?1")
  int addMediaId(Long userId, String mediaId);
}

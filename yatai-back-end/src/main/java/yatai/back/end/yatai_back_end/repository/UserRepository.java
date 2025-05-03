package yatai.back.end.yatai_back_end.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import yatai.back.end.yatai_back_end.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);
}
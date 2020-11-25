package br.com.egp.envy.repository;

import br.com.egp.envy.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    @Query("SELECT u FROM UserEntity u where u.username = :usernameOrEmail or u.email = :usernameOrEmail")
    public UserEntity findByUsernameOrEmail(@Param("usernameOrEmail") String usernameOrEmail);
}

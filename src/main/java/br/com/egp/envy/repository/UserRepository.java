package br.com.egp.envy.repository;

import br.com.egp.envy.entity.Envelope;
import br.com.egp.envy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u where u.username = :usernameOrEmail or u.email = :usernameOrEmail")
    public User findByUsernameOrEmail(@Param("usernameOrEmail") String usernameOrEmail);
}

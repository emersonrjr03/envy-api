package br.com.egp.envy.repository;

import br.com.egp.envy.entity.TransactionEntity;
import br.com.egp.envy.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<UserEntity, Integer>, PagingAndSortingRepository<UserEntity, Integer>, JpaSpecificationExecutor<UserRepository> {
    @Query("SELECT u FROM UserEntity u where u.username = :usernameOrEmail or u.email = :usernameOrEmail")
    public UserEntity findByUsernameOrEmail(@Param("usernameOrEmail") String usernameOrEmail);
}

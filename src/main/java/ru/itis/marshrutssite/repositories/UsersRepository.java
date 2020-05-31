package ru.itis.marshrutssite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.marshrutssite.models.State;
import ru.itis.marshrutssite.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);
    Optional<User> findByConfirmCode(String confirmCode);
    @Query("from User user where " +
            "(:state is null or :state = user.state) and (" +
            "upper(user.role) like concat('%', upper(:query), '%') or " +
            "upper(user.email) like concat('%', upper(:query), '%') or " +
            "upper(user.name) like concat('%', upper(:query), '%'))")
    Page<User> search(@Param("query") String query, @Param("state") State state, Pageable pageable);

}

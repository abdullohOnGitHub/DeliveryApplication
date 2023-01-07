package uz.developers.auth.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.developers.auth.entity.Users;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepo extends JpaRepository<Users, Integer> {
    Boolean existsByEmailAndIsActive(String email, Boolean b);
    Optional<Users> findByEmailAndIsActive(String email, Boolean isactive);
    List<Users> findAllByIsActive(Boolean isactive);

}

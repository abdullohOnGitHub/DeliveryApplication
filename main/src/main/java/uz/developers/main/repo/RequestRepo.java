package uz.developers.main.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.developers.main.entity.Request;

import java.util.Optional;

public interface RequestRepo extends JpaRepository<Request,Integer> {

    boolean existsByRequestUuid(String request_uuid);

    Optional<Request> findByRequestUuid(String request_uuid);
}

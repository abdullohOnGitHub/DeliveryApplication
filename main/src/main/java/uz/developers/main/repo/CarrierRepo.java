package uz.developers.main.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.developers.main.entity.Carrier;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarrierRepo extends JpaRepository<Carrier, Integer> {
    List<Carrier> findByRegionsName(String name);

    Optional<Carrier> findByFirstName(String name);

    Optional<Carrier> findByIdAndIsActive(Integer integer,Boolean b);
    List<Carrier> findAllByIsActive(Boolean b);
}

package uz.developers.main.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.developers.main.entity.Region;

import java.util.Optional;

@Repository
public interface RegionRepo extends JpaRepository<Region, Integer> {
    Optional<Region> findByName(String name);

    boolean existsByName(String name);
}

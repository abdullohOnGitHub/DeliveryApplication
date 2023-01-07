package uz.developers.main.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.developers.main.entity.District;

import java.util.List;
import java.util.Optional;

@Repository
public interface DistrictRepo extends JpaRepository<District, Integer> {
    List<District> findByRegionName(String regionName);

    Optional<District> findByName(String district_name);

    List<District> findByRegionId(Integer regionId);

}

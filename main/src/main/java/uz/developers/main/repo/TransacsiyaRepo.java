package uz.developers.main.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.developers.main.entity.Offer;
import uz.developers.main.entity.Request;
import uz.developers.main.entity.Transacsiya;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface TransacsiyaRepo extends JpaRepository<Transacsiya, Integer> {

    boolean existsByRequest(Request request);

    boolean existsByOffer(Offer offer);

    Optional<Transacsiya> findByTransacsiyaUuid(String uuid);

    @Query(value = "select sum(score) as score ,c.first_name,c.last_name\n" +
            "from transacsiya t join carrier c on c.id = t.carrier_id group by c.first_name, c.last_name having sum(score) > :kichik_score order by c.first_name",
            nativeQuery = true)
    List<Map<Integer,Object>> scorePerCarrier(@Param("kichik_score") Integer min_score);

    Integer countByCarrier_RegionsName(String name);
}

package uz.developers.main.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.developers.main.entity.Offer;
import uz.developers.main.entity.Request;
import uz.developers.main.entity.Transacsiya;

import java.util.Optional;

@Repository
public interface TransacsiyaRepo extends JpaRepository<Transacsiya, Integer> {

    boolean existsByRequest(Request request);

    boolean existsByOffer(Offer offer);

    Optional<Transacsiya> findByTransacsiyaUuid(String uuid);
}

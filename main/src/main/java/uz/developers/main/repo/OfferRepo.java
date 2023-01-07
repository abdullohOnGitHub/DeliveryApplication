package uz.developers.main.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.developers.main.entity.Offer;

import java.util.Optional;

@Repository
public interface OfferRepo extends JpaRepository<Offer,Integer> {
    boolean existsByOfferUuid(String  offer_uuid);

    Optional<Offer> findByOfferUuid(String offer_uuid);
}

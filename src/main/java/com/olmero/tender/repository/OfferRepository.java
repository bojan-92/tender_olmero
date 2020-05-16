package com.olmero.tender.repository;

import com.olmero.tender.entity.Offer;
import com.olmero.tender.entity.OfferStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

	List<Offer> findAllByTenderIdAndStatus(Long tenderId, OfferStatus status);

	List<Offer> findAllByTenderId(Long tenderId);

	List<Offer> findAllByTenderIdAndBidderIdAndStatus(Long tenderId, Long bidderId, OfferStatus status);

	List<Offer> findAllByBidderIdAndStatus(Long bidderId, OfferStatus status);

	@Modifying
	@Query("update Offer o set o.status = ?1 where o.id = ?2")
	void updateOfferStatus(OfferStatus offersStatus, Long offerId);

}

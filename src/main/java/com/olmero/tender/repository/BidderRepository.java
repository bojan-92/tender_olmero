package com.olmero.tender.repository;

import com.olmero.tender.entity.Bidder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BidderRepository extends JpaRepository<Bidder, Long> {
}

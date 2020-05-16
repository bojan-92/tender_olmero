package com.olmero.tender.service;

import com.olmero.tender.entity.Bidder;
import com.olmero.tender.entity.Issuer;
import com.olmero.tender.repository.BidderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BidderService {

	@Autowired
	private BidderRepository bidderRepository;

	public Bidder findById(Long id) {
		Optional<Bidder> bidder = bidderRepository.findById(id);
		return bidder.orElse(null);
	}

	public void save(Bidder bidder) {
		bidderRepository.save(bidder);
	}

}

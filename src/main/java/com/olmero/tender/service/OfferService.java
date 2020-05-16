package com.olmero.tender.service;


import com.olmero.tender.entity.Offer;
import com.olmero.tender.entity.OfferStatus;
import com.olmero.tender.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OfferService {

	@Autowired
	private OfferRepository offerRepository;

	public Offer findById(Long id) {
		Optional<Offer> offer = offerRepository.findById(id);
		return offer.orElse(null);
	}

	public List<Offer> findAllByTenderIdAndStatus(Long tenderId, OfferStatus status) {
		return offerRepository.findAllByTenderIdAndStatus(tenderId, status);
	}

	public List<Offer> findAllByTenderId(Long tenderId) {
		return offerRepository.findAllByTenderId(tenderId);
	}

	public List<Offer> findAllByTenderIdAndBidderIdAndStatus(Long tenderId, Long bidderId, OfferStatus status) {
		return offerRepository.findAllByTenderIdAndBidderIdAndStatus(tenderId, bidderId, status);
	}

	public List<Offer> findAllByBidderIdAndStatus(Long bidderId, OfferStatus status) {
		return offerRepository.findAllByBidderIdAndStatus(bidderId, status);
	}

	public void save(Offer offer) {
		offerRepository.save(offer);
	}

	@Transactional
	public void updateOfferStatus(OfferStatus offerStatus, Long offerId) {
		offerRepository.updateOfferStatus(offerStatus, offerId);
	}
}

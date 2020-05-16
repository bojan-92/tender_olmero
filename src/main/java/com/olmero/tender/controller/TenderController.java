package com.olmero.tender.controller;

import com.olmero.tender.entity.*;
import com.olmero.tender.exception.OfferAlreadySubmittedException;
import com.olmero.tender.exception.ResourceNotFoundException;
import com.olmero.tender.exception.RequestNotValidException;
import com.olmero.tender.service.BidderService;
import com.olmero.tender.service.IssuerService;
import com.olmero.tender.service.OfferService;
import com.olmero.tender.service.TenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tender")
public class TenderController {

	@Autowired
	private TenderService tenderService;

	@Autowired
	private IssuerService issuerService;

	@Autowired
	private OfferService offerService;

	@Autowired
	private BidderService bidderService;

	// GET

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Tender getTender(@PathVariable Long id) {
		Tender tender = tenderService.findById(id);
		if (tender == null) {
			throw new ResourceNotFoundException("Tender with id - " + id);
		}
		return tender;
	}

	@GetMapping(path = "/offer/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Offer getOffer(@PathVariable Long id) {
		Offer offer = offerService.findById(id);
		if (offer == null) {
			throw new ResourceNotFoundException("Offer with id - " + id);
		}
		return offer;
	}

	@GetMapping(path = "/{id}/offers", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Offer> getTenderOffers(@PathVariable Long id) {
		Tender tender = tenderService.findById(id);
		if (tender == null) {
			throw new ResourceNotFoundException("Tender with id - " + id);
		}
		return offerService.findAllByTenderId(id);
	}

	@GetMapping(path = "/bidder/{id}/offers", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Offer> getOffersSubmittedByBidder(@PathVariable Long id,
												  @RequestParam(required = false) Long tenderId) {
		Bidder bidder = bidderService.findById(id);
		if (bidder == null) {
			throw new ResourceNotFoundException("Bidder with id - " + id);
		}
		if (tenderId != null) {
			Tender tender = tenderService.findById(id);
			if (tender == null) {
				throw new ResourceNotFoundException("Tender with id - " + id);
			}
			return offerService.findAllByTenderIdAndBidderIdAndStatus(tenderId, id, OfferStatus.SUBMITTED);
		} else {
			return offerService.findAllByBidderIdAndStatus(id, OfferStatus.SUBMITTED);
		}
	}

	@GetMapping(path = "/issuer/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Tender> getTendersForIssuer(@PathVariable Long id) {
		Issuer issuer = issuerService.findById(id);
		if (issuer == null) {
			throw new ResourceNotFoundException("Issuer with id - " + id);
		}
		return tenderService.findAllByIssuerId(id);
	}

	// POST

	@PostMapping
	public ResponseEntity createTender(@RequestBody CreateTenderRequest tenderRequest) {
		Issuer issuer = issuerService.findById(tenderRequest.getIssuerId());
		if (issuer == null) {
			throw new ResourceNotFoundException("Issuer with id - " + tenderRequest.getIssuerId());
		}
		Tender tender = new Tender();
		if (tenderRequest.getWorkDescription() == null) {
			throw new RequestNotValidException("Work description can not be null");
		}
		tender.setWorkDescription(tenderRequest.getWorkDescription());
		tender.setIssuer(issuer);
		tenderService.save(tender);

		return new ResponseEntity(HttpStatus.CREATED);
	}

	@PostMapping("/offer")
	public ResponseEntity createOffer(@RequestBody CreateOfferRequest offerRequest) {
		Bidder bidder = bidderService.findById(offerRequest.getBidderId());
		if (bidder == null) {
			throw new ResourceNotFoundException("Bidder with id - " + offerRequest.getBidderId());
		}
		Tender tender = tenderService.findById(offerRequest.getTenderId());
		if (tender == null) {
			throw new ResourceNotFoundException("Tender with id - " + offerRequest.getTenderId());
		}
		if (offerRequest.getPrice() == null) {
			throw new RequestNotValidException("Offer price can not be null");
		}
		Offer offer = new Offer();
		offer.setBidder(bidder);
		offer.setPrice(offerRequest.getPrice());
		offer.setStatus(OfferStatus.NON_SUBMITTED);
		offer.setTender(tender);
		offerService.save(offer);

		return new ResponseEntity(HttpStatus.CREATED);
	}

	//PUT

	@PutMapping("/{tenderId}/offer/{offerId}")
	public ResponseEntity submitOffer(@PathVariable Long tenderId, @PathVariable Long offerId) {
		Tender tender = tenderService.findById(tenderId);
		if (tender == null) {
			throw new ResourceNotFoundException("Tender with id - " + tenderId);
		}
		List<Offer> offers = offerService.findAllByTenderIdAndStatus(tenderId, OfferStatus.SUBMITTED);
		if (offers != null && !offers.isEmpty()) {
			throw new OfferAlreadySubmittedException("Tender - " + tenderId + " already has submitted offer.");
		}
		Offer offer = offerService.findById(offerId);

		if(offer == null){
			throw new ResourceNotFoundException("Offer with id - " + tenderId);
		}

		if(offer.getStatus().equals(OfferStatus.SUBMITTED)){
			throw new OfferAlreadySubmittedException("Offer - " + offerId + " is already submitted.");
		}

		if (!offer.getTender().getId().equals(tenderId)) {
			throw new ResourceNotFoundException("Offer with id - " + offerId + " doesn't exist for Tender with id - " + tenderId);
		}

		offerService.updateOfferStatus(OfferStatus.SUBMITTED, offerId);

		offerService.findAllByTenderId(tenderId)
				.stream()
				.filter(o -> !o.getId().equals(offerId))
				.forEach(o1 -> offerService.updateOfferStatus(OfferStatus.REJECTED, o1.getId()));

		return new ResponseEntity(HttpStatus.OK);
	}

}

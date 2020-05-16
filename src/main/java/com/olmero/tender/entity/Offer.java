package com.olmero.tender.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "offers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Offer implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private OfferStatus status;

	@Column(name = "price", nullable = false)
	private Long price;

	@ManyToOne
	@JoinColumn(name = "tender_id")
	@JsonIgnore
	private Tender tender;

	@ManyToOne
	@JoinColumn(name = "bidder_id")
	@JsonIgnore
	private Bidder bidder;
}

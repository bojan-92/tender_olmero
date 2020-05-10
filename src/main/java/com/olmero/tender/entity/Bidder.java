package com.olmero.tender.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bidders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Bidder implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "bidder_name")
	private String bidderName;

	@OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
	private List<Offer> offers = new ArrayList<>();
}

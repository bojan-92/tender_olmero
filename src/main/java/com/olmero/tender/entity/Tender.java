package com.olmero.tender.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tenders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Tender implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "work_description")
	private String workDescription;

	@ManyToOne
	@JoinColumn(name = "issuer_id")
	private Issuer issuer;

	@OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
	private List<Offer> offers = new ArrayList<>();
}

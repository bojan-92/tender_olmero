package com.olmero.tender.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
	@JsonIgnore
	@ToString.Exclude
	private Issuer issuer;

	@OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Offer> offers = new ArrayList<>();
}

package br.com.jstack.system.acronym.domain.entity;

import java.util.ArrayList;
import java.util.List;

import br.com.jstack.system.acronym.domain.vo.Audit;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "business_unit")
public class BusinessUnit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "business_unit_seq")
	@SequenceGenerator(name = "business_unit_seq", sequenceName = "business_unit_business_unit_id_seq", allocationSize = 1)
	@Column(name = "business_unit_id")
	private Long id;
	
	private String name;
	
	private String description;
	
	private Boolean active;
	
	@Embedded
	private Audit audit = new Audit();
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(
		name = "business_unit_domain",
		joinColumns = @JoinColumn(name = "business_unit_id"),
		inverseJoinColumns = @JoinColumn(name = "business_domain_id"),
		uniqueConstraints = @UniqueConstraint(columnNames = {"business_unit_id", "business_domain_id"})
	)
	private List<BusinessDomain> businessDomains = new ArrayList<>();
	
	@OneToMany(mappedBy = "businessUnit", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<BusinessUnitDomain> businessUnitDomains = new ArrayList<>();
}
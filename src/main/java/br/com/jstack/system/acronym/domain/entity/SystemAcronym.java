package br.com.jstack.system.acronym.domain.entity;

import java.time.LocalDate;
import java.util.Set;

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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "system_acronym")
public class SystemAcronym {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "system_acronym_seq")
	@SequenceGenerator(name = "system_acronym_seq", sequenceName = "system_acronym_system_acronym_id_seq", allocationSize = 1)
	@Column(name = "system_acronym_id")
	private Long id;
	
	@Column(name = "acronym", nullable = false)
	private String acronym;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "description", columnDefinition = "text")
	private String description;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "type_id")
	private SystemAcronymType type;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "domain_id")
	private BusinessDomain domain;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "dev_team_id")
	private Team devTeam;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "biz_team_id")
	private Team bizTeam;
	
	@Column(name = "product_owner_name")
	private String productOwnerName;
	
	@Column(name = "contact_email")
	private String contactEmail;
	
	@Column(name = "critical_for_operation")
	private Boolean criticalForOperation;
	
	@Column(name = "has_personal_data")
	private Boolean hasPersonalData;
	
	@Column(name = "security_level")
	private String securityLevel;
	
	@Column(name = "compliance_requirements", columnDefinition = "text")
	private String complianceRequirements;
	
	@Column(name = "documentation_url", columnDefinition = "text")
	private String documentationUrl;
	
	@Column(name = "lifecycle_status")
	private String lifecycleStatus;
	
	@Column(name = "go_live_date")
	private LocalDate goLiveDate;
	
	@Column(name = "sunset_date")
	private LocalDate sunsetDate;
	
	@Column(name = "active")
	private Boolean active;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "acronym", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<SystemAcronymResponsibility> responsibilities;
	
	@Embedded
	private Audit audit = new Audit();
}
package br.com.jstack.system.acronym.domain.entity;

import br.com.jstack.system.acronym.domain.vo.Audit;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "system_acronym_responsibility")
public class SystemAcronymResponsibility {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "system_acronym_responsibility_seq")
	@SequenceGenerator(name = "system_acronym_responsibility_seq", sequenceName = "system_acronym_responsibility_system_acronym_responsibility_seq", allocationSize = 1)
	@Column(name = "system_acronym_responsibility_id")
	private Long id;
	
	@Column(name = "name", nullable = false, length = 150)
	private String name;
	
	@Column(name = "description", columnDefinition = "text")
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "responsibility_type_id")
	private ResponsibilityType responsibilityType;
	
	@ManyToOne
	@JoinColumn(name = "team_id")
	private Team team;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "acronym_id")
	private SystemAcronym acronym;
	
	private Boolean active;
	
	@Embedded
	private Audit audit = new Audit();
}
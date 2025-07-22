package br.com.jstack.system.acronym.domain.entity;

import br.com.jstack.system.acronym.domain.vo.Audit;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "team")
public class Team {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "team_seq")
	@SequenceGenerator(name = "team_seq", sequenceName = "team_team_id_seq", allocationSize = 1)
	@Column(name = "team_id")
	private Long id;
	
	private String name;
	
	private String description;
	
	@Embedded
	private Audit audit = new Audit();
}

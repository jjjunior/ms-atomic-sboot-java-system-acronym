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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
	
	@NotBlank(message = "The name of the Team must not be blank.")
	@Size(max = 100, message = "The name must be at most 100 characters.")
	private String name;
	
	@NotBlank(message = "The description of the Team must not be blank.")
	@Size(max = 255, message = "The description of the Team must be at most 255 characters.")
	private String description;
	
	@Embedded
	private Audit audit = new Audit();
}

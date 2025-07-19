package br.com.jstack.system.acronym.application.port.output;

import br.com.jstack.system.acronym.domain.entity.Team;

public interface TeamOutputPort extends PersistencePort<Team, Long> {
	boolean existsByName(String name);
	
	boolean existsByNameAndIdNot(String name, Long id);
}

package br.com.jstack.system.acronym.application.port.output;

import br.com.jstack.system.acronym.domain.entity.SystemAcronymResponsibility;

public interface SystemAcronymResponsibilityOutputPort extends PersistencePort<SystemAcronymResponsibility, Long> {
	boolean existsByName(String name);
	
	boolean existsByNameAndIdNot(String name, Long id);
}

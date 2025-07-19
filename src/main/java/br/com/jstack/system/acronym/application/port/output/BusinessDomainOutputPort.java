package br.com.jstack.system.acronym.application.port.output;

import br.com.jstack.system.acronym.domain.entity.BusinessDomain;

public interface BusinessDomainOutputPort extends PersistencePort<BusinessDomain, Long> {
	boolean existsByName(String name);
	
	boolean existsByNameAndIdNot(String name, Long id);
}

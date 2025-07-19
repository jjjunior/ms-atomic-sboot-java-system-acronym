package br.com.jstack.system.acronym.domain.policy;

import br.com.jstack.system.acronym.application.port.output.SystemAcronymOutputPort;
import br.com.jstack.system.acronym.domain.entity.SystemAcronym;
import br.com.jstack.system.acronym.domain.specification.Specification;
import br.com.jstack.system.acronym.domain.specification.SpecificationFactory;
import br.com.jstack.system.acronym.domain.vo.OperationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SystemAcronymPolicy implements ValidationPolicy<SystemAcronym> {
	
	private final SpecificationFactory    specFactory;
	private final SystemAcronymOutputPort outputPort;
	
	@Override
	public void validate(SystemAcronym domain, OperationType operation) {
		
		if (operation == OperationType.CREATE) {
			Specification<SystemAcronym> uniqueNameSpec = specFactory.uniqueName(outputPort::existsByName, SystemAcronym::getName);
			if (!uniqueNameSpec.isSatisfiedBy(domain)) {
				throw new IllegalArgumentException("System Acronym Type must be unique.");
			}
		}
		
		if (operation == OperationType.UPDATE) {
			Specification<SystemAcronym> uniqueNameExclIdSpec = specFactory.uniqueNameExcludingSelf(outputPort::existsByNameAndIdNot, SystemAcronym::getName, SystemAcronym::getId);
			if (!uniqueNameExclIdSpec.isSatisfiedBy(domain)) {
				throw new IllegalArgumentException("System Acronym Type must be unique (excluding self).");
			}
		}
	}
	
	@Override
	public boolean supports(OperationType operation) {
		return operation == OperationType.CREATE || operation == OperationType.UPDATE;
	}
	
	@Override
	public Class<SystemAcronym> getTargetType() {
		return SystemAcronym.class;
	}
}
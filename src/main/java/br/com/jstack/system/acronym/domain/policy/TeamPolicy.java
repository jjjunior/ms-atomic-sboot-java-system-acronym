package br.com.jstack.system.acronym.domain.policy;

import br.com.jstack.system.acronym.application.port.output.TeamOutputPort;
import br.com.jstack.system.acronym.domain.entity.Team;
import br.com.jstack.system.acronym.domain.specification.Specification;
import br.com.jstack.system.acronym.domain.specification.SpecificationFactory;
import br.com.jstack.system.acronym.domain.vo.OperationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TeamPolicy implements ValidationPolicy<Team> {
	
	private final SpecificationFactory specFactory;
	private final TeamOutputPort       outputPort;
	
	@Override
	public void validate(Team domain, OperationType operation) {
		
		if (operation == OperationType.CREATE) {
			Specification<Team> uniqueNameSpec = specFactory.uniqueName(outputPort::existsByName, Team::getName);
			if (!uniqueNameSpec.isSatisfiedBy(domain)) {
				throw new IllegalArgumentException("Team name must be unique.");
			}
		}
		
		if (operation == OperationType.UPDATE) {
			Specification<Team> uniqueNameExclIdSpec = specFactory.uniqueNameExcludingSelf(outputPort::existsByNameAndIdNot, Team::getName, Team::getId);
			if (!uniqueNameExclIdSpec.isSatisfiedBy(domain)) {
				throw new IllegalArgumentException("Team name must be unique (excluding self).");
			}
		}
	}
	
	@Override
	public boolean supports(OperationType operation) {
		return operation == OperationType.CREATE || operation == OperationType.UPDATE;
	}
	
	@Override
	public Class<Team> getTargetType() {
		return Team.class;
	}
}
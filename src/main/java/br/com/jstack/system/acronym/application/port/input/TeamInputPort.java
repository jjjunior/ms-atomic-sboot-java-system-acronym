package br.com.jstack.system.acronym.application.port.input;

import java.util.List;
import java.util.stream.Collectors;

import br.com.jstack.system.acronym.application.port.output.TeamOutputPort;
import br.com.jstack.system.acronym.application.usecase.CreateUseCase;
import br.com.jstack.system.acronym.application.usecase.DeleteByIdUseCase;
import br.com.jstack.system.acronym.application.usecase.RetrieveAllUseCase;
import br.com.jstack.system.acronym.application.usecase.RetrieveByIdUseCase;
import br.com.jstack.system.acronym.application.usecase.UpdateUseCase;
import br.com.jstack.system.acronym.domain.entity.Team;
import br.com.jstack.system.acronym.domain.policy.PolicyResolver;
import br.com.jstack.system.acronym.domain.policy.ValidationPolicy;
import br.com.jstack.system.acronym.domain.vo.OperationType;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TeamInputPort implements
	CreateUseCase<Team>,
	RetrieveByIdUseCase<Team, Long>,
	RetrieveAllUseCase<Team>,
	UpdateUseCase<Team>,
	DeleteByIdUseCase<Team, Long> {
	
	private final TeamOutputPort       outputPort;
	private final PolicyResolver<Team> policyResolver;
	
	@Override
	public Team create(@Valid Team domain) {
		ValidationPolicy<Team> policy = policyResolver.resolve(OperationType.CREATE, Team.class);
		policy.validate(domain, OperationType.CREATE);
		return outputPort.save(domain);
	}
	
	@Override
	public Team retrieveById(Long id) {
		return outputPort.findById(id);
	}
	
	@Override
	public List<Team> retrieveAll() {
		return outputPort.findAll().stream().collect(Collectors.toList());
	}
	
	@Override
	public Team update(@Valid Team domain) {
		ValidationPolicy<Team> policy = policyResolver.resolve(OperationType.UPDATE, Team.class);
		policy.validate(domain, OperationType.UPDATE);
		return outputPort.update(domain);
	}
	
	@Override
	public void deleteById(Long id) {
		outputPort.deleteById(id);
	}
}

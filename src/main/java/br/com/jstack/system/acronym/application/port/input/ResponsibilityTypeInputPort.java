package br.com.jstack.system.acronym.application.port.input;

import java.util.List;
import java.util.stream.Collectors;

import br.com.jstack.system.acronym.application.port.output.ResponsibilityTypeOutputPort;
import br.com.jstack.system.acronym.application.usecase.CreateUseCase;
import br.com.jstack.system.acronym.application.usecase.DeleteByIdUseCase;
import br.com.jstack.system.acronym.application.usecase.RetrieveAllUseCase;
import br.com.jstack.system.acronym.application.usecase.RetrieveByIdUseCase;
import br.com.jstack.system.acronym.application.usecase.UpdateUseCase;
import br.com.jstack.system.acronym.domain.entity.ResponsibilityType;
import br.com.jstack.system.acronym.domain.policy.PolicyResolver;
import br.com.jstack.system.acronym.domain.policy.ValidationPolicy;
import br.com.jstack.system.acronym.domain.vo.OperationType;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ResponsibilityTypeInputPort implements
	CreateUseCase<ResponsibilityType>,
	RetrieveByIdUseCase<ResponsibilityType, Long>,
	RetrieveAllUseCase<ResponsibilityType>,
	UpdateUseCase<ResponsibilityType>,
	DeleteByIdUseCase<ResponsibilityType, Long> {
	
	private final ResponsibilityTypeOutputPort       outputPort;
	private final PolicyResolver<ResponsibilityType> policyResolver;
	
	@Override
	public ResponsibilityType create(@Valid ResponsibilityType domain) {
		ValidationPolicy<ResponsibilityType> policy = policyResolver.resolve(OperationType.CREATE, ResponsibilityType.class);
		policy.validate(domain, OperationType.CREATE);
		return outputPort.save(domain);
	}
	
	@Override
	public ResponsibilityType retrieveById(Long id) {
		return outputPort.findById(id);
	}
	
	@Override
	public List<ResponsibilityType> retrieveAll() {
		return outputPort.findAll().stream().collect(Collectors.toList());
	}
	
	@Override
	public ResponsibilityType update(@Valid ResponsibilityType domain) {
		ValidationPolicy<ResponsibilityType> policy = policyResolver.resolve(OperationType.UPDATE, ResponsibilityType.class);
		policy.validate(domain, OperationType.UPDATE);
		return outputPort.update(domain);
	}
	
	@Override
	public void deleteById(Long id) {
		outputPort.deleteById(id);
	}
}

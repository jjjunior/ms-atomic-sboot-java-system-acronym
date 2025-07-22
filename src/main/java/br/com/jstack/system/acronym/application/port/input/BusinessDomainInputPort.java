package br.com.jstack.system.acronym.application.port.input;

import java.util.List;
import java.util.stream.Collectors;

import br.com.jstack.system.acronym.application.port.output.BusinessDomainOutputPort;
import br.com.jstack.system.acronym.application.usecase.CreateUseCase;
import br.com.jstack.system.acronym.application.usecase.DeleteByIdUseCase;
import br.com.jstack.system.acronym.application.usecase.RetrieveAllUseCase;
import br.com.jstack.system.acronym.application.usecase.RetrieveByIdUseCase;
import br.com.jstack.system.acronym.application.usecase.UpdateUseCase;
import br.com.jstack.system.acronym.domain.entity.BusinessDomain;
import br.com.jstack.system.acronym.domain.policy.BusinessDomainEnricher;
import br.com.jstack.system.acronym.domain.policy.PolicyResolver;
import br.com.jstack.system.acronym.domain.policy.ValidationPolicy;
import br.com.jstack.system.acronym.domain.vo.OperationType;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BusinessDomainInputPort implements
	CreateUseCase<BusinessDomain>,
	RetrieveByIdUseCase<BusinessDomain, Long>,
	RetrieveAllUseCase<BusinessDomain>,
	UpdateUseCase<BusinessDomain>,
	DeleteByIdUseCase<BusinessDomain, Long> {
	
	private final BusinessDomainOutputPort       outputPort;
	private final PolicyResolver<BusinessDomain> policyResolver;
	private final BusinessDomainEnricher         enricher;
	
	@Override
	public BusinessDomain create(@Valid BusinessDomain domain) {
		BusinessDomain                   businessDomain = enricher.enrichWithBusinessUnit(domain);
		ValidationPolicy<BusinessDomain> policy         = policyResolver.resolve(OperationType.CREATE, BusinessDomain.class);
		policy.validate(businessDomain, OperationType.CREATE);
		return outputPort.save(businessDomain);
	}
	
	@Override
	public BusinessDomain retrieveById(Long id) {
		return outputPort.findById(id);
	}
	
	@Override
	public List<BusinessDomain> retrieveAll() {
		return outputPort.findAll().stream().collect(Collectors.toList());
	}
	
	@Override
	public BusinessDomain update(@Valid BusinessDomain domain) {
		ValidationPolicy<BusinessDomain> policy = policyResolver.resolve(OperationType.UPDATE, BusinessDomain.class);
		policy.validate(domain, OperationType.UPDATE);
		BusinessDomain enriched = enricher.enrichWithBusinessUnit(domain);
		return outputPort.save(enriched);
	}
	
	@Override
	public void deleteById(Long id) {
		outputPort.deleteById(id);
	}
}

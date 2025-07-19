package br.com.jstack.system.acronym.framework.adapter.output.persistence;

import java.util.List;
import java.util.NoSuchElementException;

import br.com.jstack.system.acronym.application.port.output.BusinessUnitDomainOutputPort;
import br.com.jstack.system.acronym.domain.entity.BusinessUnitDomain;
import br.com.jstack.system.acronym.domain.entity.BusinessUnitDomainId;
import br.com.jstack.system.acronym.framework.adapter.output.persistence.repository.BusinessUnitDomainRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BusinessUnitDomainPersistenceAdapter implements BusinessUnitDomainOutputPort {
	
	private final BusinessUnitDomainRepository repository;
	
	@Transactional
	@Override
	public BusinessUnitDomain save(BusinessUnitDomain domain) {
		return repository.save(domain);
	}
	
	@Override
	public BusinessUnitDomain findById(BusinessUnitDomainId id) {
		return repository.findById(id)
			.orElseThrow(() -> new NoSuchElementException("BusinessUnitDomain not found with id: " + id));
	}
	
	@Override
	public List<BusinessUnitDomain> findAll() {
		return repository.findAll(Sort.by("id.businessUnitId", "id.businessDomainId"));
	}
	
	@Transactional
	@Override
	public void deleteById(BusinessUnitDomainId id) {
		repository.deleteById(id);
	}
	
	@Override
	public BusinessUnitDomain update(BusinessUnitDomain domain) {
		return null;
	}
}

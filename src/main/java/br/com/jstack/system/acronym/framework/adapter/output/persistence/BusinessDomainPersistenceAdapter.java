package br.com.jstack.system.acronym.framework.adapter.output.persistence;

import java.util.List;
import java.util.NoSuchElementException;

import br.com.jstack.system.acronym.application.port.output.BusinessDomainOutputPort;
import br.com.jstack.system.acronym.domain.entity.BusinessDomain;
import br.com.jstack.system.acronym.domain.entity.BusinessUnit;
import br.com.jstack.system.acronym.domain.entity.BusinessUnitDomain;
import br.com.jstack.system.acronym.domain.entity.BusinessUnitDomainId;
import br.com.jstack.system.acronym.framework.adapter.output.persistence.repository.BusinessDomainRepository;
import br.com.jstack.system.acronym.framework.adapter.output.persistence.repository.BusinessUnitDomainRepository;
import br.com.jstack.system.acronym.framework.adapter.output.persistence.repository.BusinessUnitRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BusinessDomainPersistenceAdapter implements BusinessDomainOutputPort {
	
	private final EntityManager                entityManager;
	private final BusinessDomainRepository     businessDomainRepository;
	private final BusinessUnitRepository       businessUnitRepository;
	private final BusinessUnitDomainRepository businessUnitDomainRepository;
	
	@Override
	public boolean existsByName(String name) {
		return businessDomainRepository.findByName(name).isPresent();
	}
	
	@Override
	public boolean existsByNameAndIdNot(String name, Long id) {
		return businessDomainRepository.findByNameAndIdNot(name, id).isPresent();
	}
	
	@Transactional
	@Override
	public BusinessDomain save(BusinessDomain domain) {
		BusinessDomain savedDomain = businessDomainRepository.saveAndFlush(domain);
		entityManager.flush();
		entityManager.clear();
		
		List<BusinessUnitDomain> relationships = domain.getBusinessUnits().stream()
			.map(unit -> createRelationship(savedDomain, unit.getId()))
			.toList();
		
		businessUnitDomainRepository.saveAll(relationships);
		savedDomain.setBusinessUnitDomains(relationships);
		
		return savedDomain;
	}
	
	@Override
	public BusinessDomain findById(Long id) {
		return businessDomainRepository.findById(id)
			.orElseThrow(() -> new NoSuchElementException("Business Domain with id " + id + " not found"));
	}
	
	@Override
	public List<BusinessDomain> findAll() {
		return businessDomainRepository.findAll(Sort.by("id").ascending());
	}
	
	@Transactional
	@Override
	public void deleteById(Long id) {
		businessDomainRepository.deleteById(id);
	}
	
	@Transactional
	@Override
	public BusinessDomain update(BusinessDomain domain) {
		BusinessDomain existing = findById(domain.getId());
		existing.setName(domain.getName());
		existing.setDescription(domain.getDescription());
		existing.setActive(domain.getActive());
		
		return businessDomainRepository.saveAndFlush(existing);
	}
	
	private BusinessUnitDomain createRelationship(BusinessDomain domain, Long unitId) {
		BusinessUnit unit = businessUnitRepository.findById(unitId)
			.orElseThrow(() -> new NoSuchElementException("Business Unit with id " + unitId + " not found"));
		
		BusinessUnitDomain relation = new BusinessUnitDomain();
		relation.setBusinessDomain(domain);
		relation.setBusinessUnit(unit);
		relation.setId(new BusinessUnitDomainId(unit.getId(), domain.getId()));
		
		return relation;
	}
}
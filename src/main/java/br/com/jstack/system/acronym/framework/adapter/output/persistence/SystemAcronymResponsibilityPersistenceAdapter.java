package br.com.jstack.system.acronym.framework.adapter.output.persistence;

import java.util.List;
import java.util.NoSuchElementException;

import br.com.jstack.system.acronym.application.port.output.SystemAcronymResponsibilityOutputPort;
import br.com.jstack.system.acronym.domain.entity.SystemAcronymResponsibility;
import br.com.jstack.system.acronym.framework.adapter.output.persistence.repository.SystemAcronymResponsibilityRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SystemAcronymResponsibilityPersistenceAdapter implements SystemAcronymResponsibilityOutputPort {
	
	private final EntityManager                         entityManager;
	private final SystemAcronymResponsibilityRepository repository;
	
	@Override
	public boolean existsByName(String name) {
		return repository.findByName(name).isPresent();
	}
	
	@Override
	public boolean existsByNameAndIdNot(String name, Long id) {
		return repository.findByNameAndIdNot(name, id).isPresent();
	}
	
	@Transactional
	@Override
	public SystemAcronymResponsibility save(SystemAcronymResponsibility domain) {
		SystemAcronymResponsibility systemAcronymType = repository.saveAndFlush(domain);
		entityManager.clear();
		return systemAcronymType;
	}
	
	@Override
	public SystemAcronymResponsibility findById(Long id) {
		return repository.findById(id)
			.orElseThrow(() -> new NoSuchElementException("Responsibility Type not found with id: " + id));
	}
	
	@Override
	public List<SystemAcronymResponsibility> findAll() {
		return repository.findAll(Sort.by("id").ascending());
	}
	
	@Transactional
	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
	
	@Transactional
	@Override
	public SystemAcronymResponsibility update(SystemAcronymResponsibility domain) {
		SystemAcronymResponsibility existing = findById(domain.getId());
		existing.setName(domain.getName());
		existing.setDescription(domain.getDescription());
		repository.saveAndFlush(existing);
		return existing;
	}
}
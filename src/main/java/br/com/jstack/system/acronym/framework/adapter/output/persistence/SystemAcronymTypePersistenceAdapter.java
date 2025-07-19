package br.com.jstack.system.acronym.framework.adapter.output.persistence;

import java.util.List;
import java.util.NoSuchElementException;

import br.com.jstack.system.acronym.application.port.output.SystemAcronymTypeOutputPort;
import br.com.jstack.system.acronym.domain.entity.SystemAcronymType;
import br.com.jstack.system.acronym.framework.adapter.output.persistence.repository.SystemAcronymTypeRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SystemAcronymTypePersistenceAdapter implements SystemAcronymTypeOutputPort {
	
	private final EntityManager               entityManager;
	private final SystemAcronymTypeRepository repository;
	
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
	public SystemAcronymType save(SystemAcronymType domain) {
		SystemAcronymType systemAcronymType = repository.saveAndFlush(domain);
		entityManager.clear();
		return systemAcronymType;
	}
	
	@Override
	public SystemAcronymType findById(Long id) {
		SystemAcronymType systemAcronymType = repository.findById(id)
			.orElseThrow(() -> new NoSuchElementException("Responsibility Type not found with id: " + id));
		return systemAcronymType;
	}
	
	@Override
	public List<SystemAcronymType> findAll() {
		return repository.findAll(Sort.by("id").ascending());
	}
	
	@Transactional
	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
	
	@Transactional
	@Override
	public SystemAcronymType update(SystemAcronymType domain) {
		SystemAcronymType existing = findById(domain.getId());
		existing.setName(domain.getName());
		existing.setDescription(domain.getDescription());
		repository.saveAndFlush(existing);
		return existing;
	}
}
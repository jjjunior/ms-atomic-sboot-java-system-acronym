package br.com.jstack.system.acronym.framework.adapter.output.persistence;

import java.util.List;
import java.util.NoSuchElementException;

import br.com.jstack.system.acronym.application.port.output.SystemAcronymOutputPort;
import br.com.jstack.system.acronym.domain.entity.SystemAcronym;
import br.com.jstack.system.acronym.framework.adapter.output.persistence.repository.SystemAcronymRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SystemAcronymPersistenceAdapter implements SystemAcronymOutputPort {
	
	private final EntityManager           entityManager;
	private final SystemAcronymRepository repository;
	
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
	public SystemAcronym save(SystemAcronym domain) {
		SystemAcronym systemAcronymType = repository.saveAndFlush(domain);
		entityManager.clear();
		return systemAcronymType;
	}
	
	@Override
	public SystemAcronym findById(Long id) {
		return repository.findById(id)
			.orElseThrow(() -> new NoSuchElementException("Responsibility Type not found with id: " + id));
	}
	
	@Override
	public List<SystemAcronym> findAll() {
		return repository.findAll(Sort.by("id").ascending());
	}
	
	@Transactional
	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
	
	@Transactional
	@Override
	public SystemAcronym update(SystemAcronym domain) {
		SystemAcronym existing = findById(domain.getId());
		existing.setName(domain.getName());
		existing.setDescription(domain.getDescription());
		repository.saveAndFlush(existing);
		return existing;
	}
}
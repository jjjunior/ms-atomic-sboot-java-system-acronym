package br.com.jstack.system.acronym.framework.adapter.output.persistence;

import java.util.List;
import java.util.NoSuchElementException;

import br.com.jstack.system.acronym.application.port.output.ResponsibilityTypeOutputPort;
import br.com.jstack.system.acronym.domain.entity.ResponsibilityType;
import br.com.jstack.system.acronym.framework.adapter.output.persistence.repository.ResponsibilityTypeRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ResponsibilityTypePersistenceAdapter implements ResponsibilityTypeOutputPort {
	
	private final EntityManager                entityManager;
	private final ResponsibilityTypeRepository repository;
	
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
	public ResponsibilityType save(ResponsibilityType domain) {
		ResponsibilityType responsibilityType = repository.saveAndFlush(domain);
		entityManager.clear();
		return responsibilityType;
	}
	
	@Override
	public ResponsibilityType findById(Long id) {
		ResponsibilityType responsibilityType = repository.findById(id)
			.orElseThrow(() -> new NoSuchElementException("Responsibility Type not found with id: " + id));
		return responsibilityType;
	}
	
	@Override
	public List<ResponsibilityType> findAll() {
		return repository.findAll(Sort.by("id").ascending());
	}
	
	@Transactional
	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
	
	@Transactional
	@Override
	public ResponsibilityType update(ResponsibilityType domain) {
		ResponsibilityType existing = findById(domain.getId());
		existing.setName(domain.getName());
		existing.setDescription(domain.getDescription());
		existing.setActive(domain.getActive());
		repository.saveAndFlush(existing);
		return existing;
	}
}
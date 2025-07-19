package br.com.jstack.system.acronym.framework.adapter.output.persistence;

import java.util.List;
import java.util.NoSuchElementException;

import br.com.jstack.system.acronym.application.port.output.TeamOutputPort;
import br.com.jstack.system.acronym.domain.entity.Team;
import br.com.jstack.system.acronym.framework.adapter.output.persistence.repository.TeamRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TeamPersistenceAdapter implements TeamOutputPort {
	
	private final EntityManager  entityManager;
	private final TeamRepository repository;
	
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
	public Team save(Team domain) {
		Team team = repository.saveAndFlush(domain);
		entityManager.clear();
		return team;
	}
	
	@Override
	public Team findById(Long id) {
		Team team = repository.findById(id)
			.orElseThrow(() -> new NoSuchElementException("Responsibility Type not found with id: " + id));
		return team;
	}
	
	@Override
	public List<Team> findAll() {
		return repository.findAll(Sort.by("id").ascending());
	}
	
	@Transactional
	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
	
	@Transactional
	@Override
	public Team update(Team domain) {
		Team existing = findById(domain.getId());
		existing.setName(domain.getName());
		existing.setDescription(domain.getDescription());
		repository.saveAndFlush(existing);
		return existing;
	}
}
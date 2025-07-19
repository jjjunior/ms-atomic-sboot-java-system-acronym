package br.com.jstack.system.acronym.framework.adapter.output.persistence.repository;

import java.util.Optional;

import br.com.jstack.system.acronym.domain.entity.ResponsibilityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponsibilityTypeRepository extends JpaRepository<ResponsibilityType, Long> {
	Optional<ResponsibilityType> findByName(String name);
	
	Optional<ResponsibilityType> findByNameAndIdNot(String name, Long id);
}
package br.com.jstack.system.acronym.framework.adapter.output.persistence.repository;

import java.util.Optional;

import br.com.jstack.system.acronym.domain.entity.SystemAcronymResponsibility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemAcronymResponsibilityRepository extends JpaRepository<SystemAcronymResponsibility, Long> {
	Optional<SystemAcronymResponsibility> findByName(String name);
	
	Optional<SystemAcronymResponsibility> findByNameAndIdNot(String name, Long id);
}
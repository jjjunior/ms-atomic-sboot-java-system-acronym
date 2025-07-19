package br.com.jstack.system.acronym.framework.adapter.output.persistence.repository;

import java.util.Optional;

import br.com.jstack.system.acronym.domain.entity.BusinessUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessUnitRepository extends JpaRepository<BusinessUnit, Long> {
	Optional<BusinessUnit> findByName(String name);
	
	Optional<BusinessUnit> findByNameAndIdNot(String name, Long id);
}
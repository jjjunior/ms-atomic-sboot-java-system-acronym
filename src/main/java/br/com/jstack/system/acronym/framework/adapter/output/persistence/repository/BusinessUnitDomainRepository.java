package br.com.jstack.system.acronym.framework.adapter.output.persistence.repository;

import br.com.jstack.system.acronym.domain.entity.BusinessUnitDomain;
import br.com.jstack.system.acronym.domain.entity.BusinessUnitDomainId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessUnitDomainRepository extends JpaRepository<BusinessUnitDomain, BusinessUnitDomainId> {
}

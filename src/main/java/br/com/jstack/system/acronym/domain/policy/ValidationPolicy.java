package br.com.jstack.system.acronym.domain.policy;

import br.com.jstack.system.acronym.domain.vo.OperationType;

public interface ValidationPolicy<T> {
	
	boolean supports(OperationType operation);
	
	void validate(T target, OperationType operation);
	
	Class<T> getTargetType();
}
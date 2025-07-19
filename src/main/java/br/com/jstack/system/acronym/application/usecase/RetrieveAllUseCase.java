package br.com.jstack.system.acronym.application.usecase;

import java.util.List;

public interface RetrieveAllUseCase<T> {
	List<T> retrieveAll();
}
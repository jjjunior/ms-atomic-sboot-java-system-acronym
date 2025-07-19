package br.com.jstack.system.acronym.application.usecase;

public interface RetrieveByIdUseCase<T, ID> {
	T retrieveById(ID id);
}
package br.com.jstack.system.acronym.application.usecase;

public interface DeleteByIdUseCase<T, ID> {
	void deleteById(ID id);
}
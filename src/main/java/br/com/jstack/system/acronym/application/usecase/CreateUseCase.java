package br.com.jstack.system.acronym.application.usecase;

public interface CreateUseCase<T> {
	T create(T domain);
}

package br.com.jstack.system.acronym.framework.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Slf4j
@Configuration
public class ValidationConfig {
	
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		source.setBasename("ValidationMessages");
		source.setDefaultEncoding("UTF-8");
		
		try {
			String testMessage = source.getMessage("test.key", null, null);
			log.info("✔ ValidationMessages.properties carregado com sucesso. Exemplo de mensagem: {}", testMessage);
		} catch (Exception e) {
			log.error("❌ Falha ao carregar ValidationMessages.properties ou chave 'test.key' não encontrada.", e);
		}
		
		return source;
	}
	
	@Bean
	public LocalValidatorFactoryBean getValidator() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());
		return bean;
	}
}
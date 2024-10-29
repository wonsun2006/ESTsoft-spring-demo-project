package com.estsoft.springdemoproject.config;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.jasypt.iv.RandomIvGenerator;
import org.jasypt.salt.RandomSaltGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@Configuration
@EnableEncryptableProperties
public class JasyptConfigAES {
	@Value("${jasypt.encryptor.password}")
	private String password;

	@Bean("jasyptEncryptorAES")
	public StringEncryptor stringEncryptor() {
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
		encryptor.setPassword(password);
		encryptor.setIvGenerator(new RandomIvGenerator());
		encryptor.setSaltGenerator(new RandomSaltGenerator());
		return encryptor;
	}
}


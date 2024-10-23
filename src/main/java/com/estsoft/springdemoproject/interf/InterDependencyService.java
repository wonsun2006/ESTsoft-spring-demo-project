package com.estsoft.springdemoproject.interf;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class InterDependencyService {
	private final Inter inter;

	public InterDependencyService(@Qualifier("interImplA") Inter inter) {
		this.inter = inter;
	}

	public void printMethod(){
		inter.method();
	}
}

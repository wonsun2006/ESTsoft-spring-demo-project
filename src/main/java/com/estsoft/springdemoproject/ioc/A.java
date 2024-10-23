package com.estsoft.springdemoproject.ioc;

public class A {
	Inter inter;
	public A(Inter inter){
		this.inter=inter;
	}

	public void method(){
		// B objectB = new B();
		// objectB.methodB();

		C objectC = new C();
		objectC.method();
	}

	public void method2(){
		inter.method();
	}
}

package com.matt.damon.springmvc;

import org.junit.Test;

import com.google.common.base.Supplier;

public class TestSupplier {

	@Test
	public void test() {
		
		Supplier<String> getHtml = new Supplier<String>() {
            @Override
            public String get() {
                return "123";
            }
        };
		
        System.out.println(getHtml.get());
	}

	
}

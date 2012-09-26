package org.junit.contrib.java.security;

import java.security.Provider;
import java.security.Security;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.Test;

public class AddProviderTest {
	private static final String PROVIDER_NAME = "example";
	
	@Rule
	public AddProvider rule = new AddProvider(new ExampleProvider());
	
	@Test
	public void exampleTest() {
		Assert.assertNotNull("Missing expected provider", Security.getProvider(PROVIDER_NAME));
	}
	
	@AfterClass
	public static void tearDown() {
		Assert.assertNull("Provider should have been removed", Security.getProvider(PROVIDER_NAME));
	}
	
	public static class ExampleProvider extends Provider {
		private static final long serialVersionUID = 1L;

		protected ExampleProvider() {
			super(PROVIDER_NAME, 1.0, PROVIDER_NAME);
		}
	}
}

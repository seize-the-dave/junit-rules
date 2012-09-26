package org.junit.contrib.java.security;

import java.security.Provider;
import java.security.Security;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

public class RemoveProviderTest {
	private static Provider PROVIDER;
	
	@BeforeClass
	public static void identifyProvider() {
		PROVIDER = Security.getProviders()[0];
	}
	
	@Rule
	public RemoveProvider rule = new RemoveProvider(PROVIDER);
	
	@Test
	public void exampleTest() {
		Assert.assertNull("Provider should have been removed", Security.getProvider(PROVIDER.getName()));
	}
	
	@AfterClass
	public static void tearDown() {
		Assert.assertNotNull("Missing expected provider", Security.getProvider(PROVIDER.getName()));
	}
}

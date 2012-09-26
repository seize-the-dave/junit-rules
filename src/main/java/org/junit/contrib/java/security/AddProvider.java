package org.junit.contrib.java.security;

import java.security.Provider;
import java.security.Security;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.rules.ExternalResource;

public final class AddProvider extends ExternalResource {
	private Provider[] candidates;
	private List<Provider> added = new ArrayList<Provider>();
	
	public AddProvider(Provider... providers) {
		this.candidates = providers;
	}
	
	@Override
	public void before() {
		List<Provider> current = Arrays.asList(Security.getProviders());
		for (Provider p : candidates) {
			if (!current.contains(p)) {
				Security.addProvider(p);
				added.add(p);
			}
		}
	}

	@Override
	public void after() {
		if (added.size() == 0) {
			return;
		}
		for (Provider p : added) {
			Security.removeProvider(p.getName());
		}
	}

}

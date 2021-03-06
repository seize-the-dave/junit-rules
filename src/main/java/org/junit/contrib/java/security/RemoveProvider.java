package org.junit.contrib.java.security;

import java.security.Provider;
import java.security.Security;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.rules.ExternalResource;

public final class RemoveProvider extends ExternalResource {
	private Provider[] candidates;
	private List<Provider> removed = new ArrayList<Provider>();
	
	public RemoveProvider(Provider... providers) {
		this.candidates = providers;
	}
	
	@Override
	public void before() {
		List<Provider> current = Arrays.asList(Security.getProviders());
		for (Provider p : candidates) {
			if (current.contains(p)) {
				Security.removeProvider(p.getName());
				removed.add(p);
			}
		}
	}

	@Override
	public void after() {
		if (removed.size() == 0) {
			return;
		}
		for (Provider p : removed) {
			Security.addProvider(p);
		}
	}

}

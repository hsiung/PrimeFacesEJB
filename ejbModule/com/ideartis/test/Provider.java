package com.ideartis.test;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.inject.Named;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

@Entity
@Named
public class Provider {
	private Long id;
	private String name;
	private Set<EnvProvider> envProviders;
	
	@Id @GeneratedValue()
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
		
	@OneToMany(cascade=CascadeType.ALL)
	@OrderBy("orderedName")
	public Set<EnvProvider> getEnvProviders() {
		return envProviders;
	}

	public void setEnvProviders (Set<EnvProvider> envProviders) {
		this.envProviders = envProviders;
	}

	// creates the list of ProviderUrls by applying the Overriding mapping
	public void generateProviderUrlsToUpload() {
		Set<EnvProvider> envProviders = getEnvProviders();
		Iterator<EnvProvider> iter1 = envProviders.iterator();
		while (iter1.hasNext()) {
			Set<ProviderUrl> providerUrls = new LinkedHashSet<ProviderUrl>();
			EnvProvider envProvider = iter1.next();
			Set<Kind> kinds = envProvider.getKind().getOverridenKinds();
			Iterator<Kind> iter = kinds.iterator();
			while(iter.hasNext()) {
				EnvProvider ep = getEnvProvider(iter.next());
				providerUrls.addAll(ep.getProviderUrls());
			}
			envProvider.setProviderUrlsToUpload(providerUrls);
		}
	}
	
	// find the EnvProvider with the given Kind
	@Transient
	public EnvProvider getEnvProvider(Kind kind) {
		Iterator<EnvProvider> iter = getEnvProviders().iterator();
		while (iter.hasNext()) {
			EnvProvider envProvider = iter.next();
			if (envProvider.getKind().equals(kind)) {
				return envProvider;
			}
		}
		return null;
	}
	
}

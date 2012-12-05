package com.ideartis.test;

import java.util.Set;

import javax.inject.Named;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@Entity
@Named
public class ProviderGroup {
	private Long id;
	private String name;
	private Set<Provider> providers;
	private Set<Kind> kinds;
	private int taState = 1; // Track Awareness (state 2), no Track Awareness (state 1)
	
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
	public Set<Provider> getProviders() {
		return providers;
	}

	public void setProviders(Set<Provider> providers) {
		this.providers = providers;
	}

	@OneToMany(cascade=CascadeType.ALL)
	@OrderBy("envId,name")
	public Set<Kind> getKinds() {
		return kinds;
	}

	public void setKinds(Set<Kind> kinds) {
		this.kinds = kinds;
	}

	public int getTaState() {
		return taState;
	}

	public void setTaState(int taState) {
		this.taState = taState;
	}
	
}

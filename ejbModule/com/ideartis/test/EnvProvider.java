package com.ideartis.test;

import java.util.Set;

import javax.inject.Named;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
@Named
public class EnvProvider {
	private Long id;
	private String name;
	private String orderedName; //necessary to order PTA before PROD
	private Kind kind;
	private Set<ProviderUrl> providerUrls;
	private Set<ProviderUrl> providerUrlsToUpload;
	
	@Id @GeneratedValue()
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		if ((name == null) && (getKind() != null)) {
			setName(getKind().getFullName());
		}
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getOrderedName() {
		if ((orderedName == null) && (getKind() != null)) {
			setOrderedName(getKind().getEnvId().ordinal() + getKind().getName());
		}
		return orderedName;
	}

	public void setOrderedName(String orderedName) {
		this.orderedName = orderedName;
	}

	@OneToOne
	public Kind getKind() {
		return kind;
	}

	public void setKind(Kind kind) {
		this.kind = kind;
	}

	@OneToMany(cascade=CascadeType.ALL)
	public Set<ProviderUrl> getProviderUrls() {
		return providerUrls;
	}

	public void setProviderUrls(Set<ProviderUrl> providerUrls) {
		this.providerUrls = providerUrls;
	}

	@Transient
	public Set<ProviderUrl> getProviderUrlsToUpload() {
		return providerUrlsToUpload;
	}
	
	public void setProviderUrlsToUpload(Set<ProviderUrl> providerUrlsToUpload) {
		this.providerUrlsToUpload = providerUrlsToUpload;
	}
	
}

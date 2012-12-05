package com.ideartis.test;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.inject.Named;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

@Entity
@Named
public class Kind {
	private Long id;
	private String name;
	private String fullName;
	private EnvId envId;
	private Set<Kind> kinds;
	
	@ManyToMany()
	@OrderBy("name")
	public Set<Kind> getKinds() {
		return kinds;
	}

	public void setKinds(Set<Kind> kinds) {
		this.kinds = kinds;
	}
	
	public void addKind(Kind kind) {
		kinds.add(kind);
	}
	
	public void removeKind(Kind kind) {
		kinds.remove(kind);
	}
	
	@Id
	@GeneratedValue()
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

	public String getFullName() {
		if ((fullName == null) && (getEnvId() != null)) {
			if (getName().isEmpty()) {
				setFullName(getEnvId().name());
			} else {
				setFullName(getEnvId().name() + "-" + getName());
			}
		}
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Enumerated
	public EnvId getEnvId() {
		return envId;
	}

	public void setEnvId(EnvId envId) {
		this.envId = envId;
	}

	@Transient
	public String getVisualizedName() {
		if ((getName() != null) && getName().isEmpty()) {
			return "_";
		} else {
			return getName();
		}
	}
	
	// OverridingKind = the Kind if it is overriding other Kinds, otherwise null
	@Transient
	public Kind getOverridingKind() {
		if (isOverriding()) {
			return this;
		} else {
			return null;
		}
	}
	
	// OverriddenKinds = the Kinds that this Kind are overriding if it is an overriding Kind, otherwise this Kind
	@Transient
	public Set<Kind> getOverridenKinds() {
		if (isOverriding()) {
			return getKinds();
		} else {
			return new LinkedHashSet<Kind>(Arrays.asList(this));
		}
	}
	
	// UnusedKind = Kind that is overriding and not overridden (by its overrider)
	@Transient
	public boolean isUnused() {
		return (isOverriding() && !isOverriden());
	}
	
	@Transient
	public boolean isOverriden() {
		return (hasKinds() && getKinds().contains(this));
	}

	@Transient
	public boolean isOverriding() {
		return hasKinds();
	}
	
	@Transient
	public boolean hasKinds() {
		return ((getKinds() != null) && (!getKinds().isEmpty()));
	}

}

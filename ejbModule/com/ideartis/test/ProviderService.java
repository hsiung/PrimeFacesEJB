package com.ideartis.test;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Session Bean implementation class ProviderService
 */
@Stateless
@Named
public class ProviderService {
	@PersistenceContext
	private EntityManager em;

	private Provider provider;
	private Long providerId;
	
	public Provider getProvider() {
		System.out.println("getProvider " + provider.getName() + " id" + provider.getId());
		return provider;
	}
	
	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public Long getProviderId() {
		return providerId;
	}
	
	public void setProviderId(Long providerId) {
		this.providerId = providerId;
		fetchProvider();
	}
	
	private void fetchProvider() {
		Query query = em.createQuery("select provider from Provider provider join fetch provider.envProviders envProvider left join fetch envProvider.providerUrls where provider.id='"
						+ providerId + "'");
		query.getSingleResult();
		query = em.createQuery("select provider from Provider provider join fetch provider.envProviders envProvider left join fetch envProvider.kind kind left join fetch kind.kinds where provider.id='"
				+ providerId + "'");
		provider = (Provider) query.getSingleResult();		
		System.out.println("Fetch provider " + provider);
	}

	public void save() throws Exception {
		try {
			provider = em.merge(provider);
			System.out.println(provider.getName() + " persisted");
		} catch (Exception e) {
			throw new Exception("Cannot persist Provider " + provider.getName() + " Exception :" + e);
		}
	}

	public List<Provider> getProviders() {
		String ejbql = "select provider from Provider provider order by provider.name";
		Query query = em.createQuery(ejbql);
		List<Provider> providers = query.getResultList();
		System.out.println("getProviders: " + providers.size());
		return providers;
	}

}

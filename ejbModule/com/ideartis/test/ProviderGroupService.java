package com.ideartis.test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Session Bean implementation class ProviderGroupService
 */
@Stateless
@Named
public class ProviderGroupService {
	@PersistenceContext
	private EntityManager em;

	private ProviderGroup providerGroup;
	private Long providerGroupId;

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public ProviderGroup getProviderGroup() {
		return providerGroup;
	}

	public void setProviderGroup(ProviderGroup providerGroup) {
		this.providerGroup = providerGroup;
	}

	public Long getProviderGroupId() {
		return providerGroupId;
	}

	public void setProviderGroupId(Long providerGroupId) {
		this.providerGroupId = providerGroupId;
		fetchProviderGroup();
	}

	private void fetchProviderGroup() {
		// pb: "join fetch <some empty set>" -> "No entity found for query" Exception
		// sol: "left join fetch <some empty set>" -> works correctly
		// multiple join fetch to retrieve several parallel attributes 
		System.out.println("Fetching..");
		Query query = em.createQuery("select providerGroup from ProviderGroup providerGroup join fetch providerGroup.providers providers where providerGroup.id='"
						+ providerGroupId + "'");
		query.getSingleResult();
		query = em.createQuery("select providerGroup from ProviderGroup providerGroup join fetch providerGroup.kinds kind left join fetch kind.kinds where providerGroup.id='"
						+ providerGroupId + "'");
		providerGroup = (ProviderGroup) query.getSingleResult();
		System.out.println("Fetch provider " + providerGroup);
	}

	public void save() throws Exception {
		try {
			providerGroup = em.merge(providerGroup);
			System.out.println(providerGroup.getName() + " persisted as " + providerGroup.getId());
		} catch (Exception e) {
			throw new Exception("Exception in ProviderGroupService.save: Cannot persist ProviderGroup "
					+ providerGroup.getName() + " Exception :" + e);
		}
	}

	public List<ProviderGroup> getProviderGroups() {
		String ejbql = "select providerGroup from ProviderGroup providerGroup order by providerGroup.name";
		Query query = em.createQuery(ejbql);
		List<ProviderGroup> providerGroups = query.getResultList();
		System.out.println("getProviderGroups: " + providerGroups.size());
		return providerGroups;
	}

	public void createProviderGroup() throws Exception {
		LinkedHashSet<Provider> providers = new LinkedHashSet<Provider>();
		// set the Kinds
		Kind env1 = new Kind();
		env1.setName("");
		env1.setEnvId(EnvId.ET);
		Kind env2 = new Kind();
		env2.setName("REL");
		env2.setEnvId(EnvId.ET);
		Kind env3 = new Kind();
		env3.setName("HEAD");
		env3.setEnvId(EnvId.ET);
		Kind env4 = new Kind();
		env4.setName("FIX");
		env4.setEnvId(EnvId.ET);
		Kind env5 = new Kind();
		env5.setName("");
		env5.setEnvId(EnvId.IT);
		Kind env6 = new Kind();
		env6.setName("A1");
		env6.setEnvId(EnvId.IT);
		Kind env7 = new Kind();
		env7.setName("A2");
		env7.setEnvId(EnvId.IT);
		Kind env8 = new Kind();
		env8.setName("A3");
		env8.setEnvId(EnvId.IT);
		Kind env9 = new Kind();
		env9.setName("A4");
		env9.setEnvId(EnvId.IT);
		Kind env10 = new Kind();
		env10.setName("B1");
		env10.setEnvId(EnvId.PTA);
		Kind env11 = new Kind();
		env11.setName("B2");
		env11.setEnvId(EnvId.PTA);
		Kind env12 = new Kind();
		env12.setName("B3");
		env12.setEnvId(EnvId.PTA);
		Kind env13 = new Kind();
		env13.setName("");
		env13.setEnvId(EnvId.PROD);
		Kind env14 = new Kind();
		env14.setName("T1");
		env14.setEnvId(EnvId.PROD);
		Kind env15 = new Kind();
		env15.setName("T2");
		env15.setEnvId(EnvId.PROD);
		Kind env18 = new Kind();
		env18.setName("ET");
		env18.setEnvId(EnvId.ET);
		Kind env19 = new Kind();
		env19.setName("");
		env19.setEnvId(EnvId.PTA);
		// set ProviderUrls
		for (int i = 0; i < 20; i++) {
			ProviderUrl url1 = new ProviderUrl();
			url1.setName("https://host:7001/services/MyService1");
			ProviderUrl url2 = new ProviderUrl();
			url2.setName("https://host:7001/services/MyService2");
			ProviderUrl url3 = new ProviderUrl();
			url3.setName("https://host:7001/services/MyService3");
			ProviderUrl url4 = new ProviderUrl();
			url4.setName("https://host:7001/services/MyService4");
			ProviderUrl url5 = new ProviderUrl();
			url5.setName("https://host:7001/services/MyService5");
			ProviderUrl url6 = new ProviderUrl();
			url6.setName("https://host:7001/services/MyService6");
			ProviderUrl url7 = new ProviderUrl();
			url7.setName("https://host:7001/services/MyService7");
			ProviderUrl url8 = new ProviderUrl();
			url8.setName("https://host:7001/services/MyService8");
			ProviderUrl url9 = new ProviderUrl();
			url9.setName("https://host:7001/services/MyService9");
			ProviderUrl url10 = new ProviderUrl();
			url10.setName("https://host:7001/services/MyService10");
			ProviderUrl url11 = new ProviderUrl();
			url11.setName("https://host:7001/services/MyService11");
			ProviderUrl url12 = new ProviderUrl();
			url12.setName("https://host:7001/services/MyService12");
			ProviderUrl url13 = new ProviderUrl();
			url13.setName("https://host:7001/services/MyService13");
			ProviderUrl url14 = new ProviderUrl();
			url14.setName("https://host:7001/services/MyService14");
			ProviderUrl url15 = new ProviderUrl();
			url15.setName("https://host:7001/services/MyService15");
			ProviderUrl url16 = new ProviderUrl();
			url16.setName("https://host:7001/services/MyService16");
			ProviderUrl url17 = new ProviderUrl();
			url17.setName("https://host:7001/services/MyService17");
			ProviderUrl url18 = new ProviderUrl();
			url18.setName("https://host:7001/services/MyService18");
			ProviderUrl url19 = new ProviderUrl();
			url19.setName("https://host:7001/services/MyService19");
			// set EnvProviders
			EnvProvider ep1 = new EnvProvider();
			ep1.setKind(env1);
			EnvProvider ep2 = new EnvProvider();
			ep2.setKind(env2);
			EnvProvider ep3 = new EnvProvider();
			ep3.setKind(env3);
			EnvProvider ep4 = new EnvProvider();
			ep4.setKind(env4);
			EnvProvider ep5 = new EnvProvider();
			ep5.setKind(env5);
			EnvProvider ep6 = new EnvProvider();
			ep6.setKind(env6);
			EnvProvider ep7 = new EnvProvider();
			ep7.setKind(env7);
			EnvProvider ep8 = new EnvProvider();
			ep8.setKind(env8);
			EnvProvider ep9 = new EnvProvider();
			ep9.setKind(env9);
			EnvProvider ep10 = new EnvProvider();
			ep10.setKind(env10);
			EnvProvider ep11 = new EnvProvider();
			ep11.setKind(env11);
			EnvProvider ep12 = new EnvProvider();
			ep12.setKind(env12);
			EnvProvider ep13 = new EnvProvider();
			ep13.setKind(env13);
			EnvProvider ep14 = new EnvProvider();
			ep14.setKind(env14);
			EnvProvider ep15 = new EnvProvider();
			ep15.setKind(env15);
			EnvProvider ep18 = new EnvProvider();
			ep18.setKind(env18);
			EnvProvider ep19 = new EnvProvider();
			ep19.setKind(env19);
			ep1.setProviderUrls(new LinkedHashSet<ProviderUrl>(Arrays
					.asList(url1)));
			ep2.setProviderUrls(new LinkedHashSet<ProviderUrl>(Arrays
					.asList(url2)));
			ep3.setProviderUrls(new LinkedHashSet<ProviderUrl>(Arrays
					.asList(url3)));
			ep4.setProviderUrls(new LinkedHashSet<ProviderUrl>(Arrays
					.asList(url4)));
			ep5.setProviderUrls(new LinkedHashSet<ProviderUrl>(Arrays
					.asList(url5)));
			ep6.setProviderUrls(new LinkedHashSet<ProviderUrl>(Arrays
					.asList(url6)));
			ep7.setProviderUrls(new LinkedHashSet<ProviderUrl>(Arrays
					.asList(url7)));
			ep8.setProviderUrls(new LinkedHashSet<ProviderUrl>(Arrays
					.asList(url8)));
			ep9.setProviderUrls(new LinkedHashSet<ProviderUrl>(Arrays
					.asList(url9)));
			ep10.setProviderUrls(new LinkedHashSet<ProviderUrl>(Arrays
					.asList(url10)));
			ep11.setProviderUrls(new LinkedHashSet<ProviderUrl>(Arrays
					.asList(url11)));
			ep12.setProviderUrls(new LinkedHashSet<ProviderUrl>(Arrays
					.asList(url12)));
			ep13.setProviderUrls(new LinkedHashSet<ProviderUrl>(Arrays
					.asList(url13)));
			ep14.setProviderUrls(new LinkedHashSet<ProviderUrl>(Arrays.asList(
					url14, url16)));
			ep15.setProviderUrls(new LinkedHashSet<ProviderUrl>(Arrays.asList(
					url15, url17)));
			ep18.setProviderUrls(new LinkedHashSet<ProviderUrl>(Arrays
					.asList(url18)));
			ep19.setProviderUrls(new LinkedHashSet<ProviderUrl>(Arrays
					.asList(url19)));
			// set Provider
			Provider provider = new Provider();
			provider.setName(randomString());
			provider.setEnvProviders(new LinkedHashSet<EnvProvider>(Arrays
					.asList(ep1, ep2, ep3, ep4, ep5, ep6, ep7, ep8, ep9, ep10,
							ep11, ep12, ep13, ep14, ep15, ep18, ep19)));
			providers.add(provider);
		}
		// set ProviderGroup
		providerGroup = new ProviderGroup();
		providerGroup.setName("PG" + randomString());
		providerGroup.setProviders(providers);
		providerGroup.setKinds(new LinkedHashSet<Kind>(Arrays.asList(env1,
				env2, env3, env4, env5, env6, env7, env8, env9, env10, env11,
				env12, env13, env14, env15, env18, env19)));
		em.persist(providerGroup);
		switchTaState(1);
	}
	
	public void switchTaState1() throws Exception {
		switchTaState(1);
	}
	public void switchTaState2() throws Exception {
		switchTaState(2);
	}
	
	public void switchTaState(int taState) throws Exception {
		Set<Kind> kinds = providerGroup.getKinds();
		Kind t0Kind = null, t1Kind = null, t2Kind = null;
		Iterator<Kind> iter = kinds.iterator();
		while (iter.hasNext()) {
			Kind k = iter.next();
			if ("PROD".equals(k.getEnvId().name())) {
				if ("".equals(k.getName())) {
					t0Kind = k;
				}
				if ("T1".equals(k.getName())) {
					t1Kind = k;
				}
				if ("T2".equals(k.getName())) {
					t2Kind = k;
				}
			}
		}
		switch (taState) {
		case 1: // track unaware
			t0Kind.setKinds(new LinkedHashSet<Kind>(Arrays.asList(t1Kind, t2Kind)));
			t1Kind.setKinds(new LinkedHashSet<Kind>(Arrays.asList(t1Kind, t2Kind)));
			t2Kind.setKinds(new LinkedHashSet<Kind>(Arrays.asList(t1Kind, t2Kind)));
			break;
		case 2: // track aware, T1 isolation
			t0Kind.setKinds(new LinkedHashSet<Kind>(Arrays.asList(t2Kind)));
			t1Kind.setKinds(new LinkedHashSet<Kind>(Arrays.asList(t1Kind)));
			t2Kind.setKinds(new LinkedHashSet<Kind>(Arrays.asList(t2Kind)));
			break;
		}
		providerGroup.setTaState(taState);
		save();
	}

	private String randomString() {
		final int LENGTH = 6;
		StringBuffer sb = new StringBuffer();
		for (int x = 0; x < LENGTH; x++) {
			sb.append((char) ((int) (Math.random() * 26) + 97));
		}
		return sb.toString();
	}

}

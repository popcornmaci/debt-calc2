package hu.popcornmaci.dao;

/*
 * #%L
 * debt-calc-dao
 * %%
 * Copyright (C) 2016 Faculty of Informatics, University of Debrecen
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EFMManager {
	private static EntityManager em;
	private static EntityManagerFactory emf;

	private EFMManager() {

	}

	public static EntityManagerFactory getFactory() {
		if (emf == null) {
			emf = Persistence.createEntityManagerFactory("JPA");
		}
		return emf;
	}

	public static EntityManager getManager() {
		if (em == null) {
			em = getFactory().createEntityManager();
		}
		return em;
	}

	public static void close() {
		if (em != null) {
			em.close();
		}
		if (emf != null) {
			emf.close();
		}
	}
}

package com.ciudades.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.ciudades.model.*;
import com.ciudades.util.*;

public class CiudadDAO {
	
	public void insertCiudad(Ciudad c) {
		Transaction transaction=null;
		try (Session session=HibernateUtil.getSessionFactory().openSession()){
			transaction=session.beginTransaction();
			session.persist(c);
			transaction.commit();
			System.out.println("Ciudad insertada con éxito");
		} catch (Exception e) {
			if (transaction!=null) {
				System.out.println("Error al insertar ciudad");
				transaction.rollback();
			}
		}
	}
	
	public void updateCiudad(Ciudad c) {
		Transaction transaction=null;
		try (Session session=HibernateUtil.getSessionFactory().openSession()){
			transaction=session.beginTransaction();
			session.merge(c);
			transaction.commit();
			System.out.println("Ciudad actualizada con éxito");
		} catch (Exception e) {
			if (transaction!=null) {
				System.out.println("Error al actualizar ciudad");
				transaction.rollback();
			}
		}
	}
	
	public void deleteciudad(int id) {
		Transaction transaction=null;
		Ciudad c=null;
		try (Session session=HibernateUtil.getSessionFactory().openSession()){
			transaction=session.beginTransaction();
			c=session.get(Ciudad.class, id);
			session.remove(c);
			transaction.commit();
			System.out.println("Ciudad borrada con éxito");
		} catch (Exception e) {
			if (transaction!=null) {
				System.out.println("Error al borrar ciudad");
				transaction.rollback();
			}
		}
	}
	
	public Ciudad selectCiudadById(int id) {
		Transaction transaction=null;
		Ciudad c=null;
		try (Session session=HibernateUtil.getSessionFactory().openSession()){
			transaction=session.beginTransaction();
			c=session.get(Ciudad.class, id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction!=null) {
				System.out.println("Error al seleccionar ciudad");
				transaction.rollback();
			}
		}
		return c;
	}
	
	public List<Ciudad> selectAllCiudades() {
		Transaction transaction=null;
		List<Ciudad> ciudades=null;
		Ciudad c=null;
		try (Session session=HibernateUtil.getSessionFactory().openSession()){
			transaction=session.beginTransaction();
			ciudades=session.createQuery("from Ciudad",Ciudad.class).getResultList();
			transaction.commit();
		} catch (Exception e) {
			if (transaction!=null) {
					System.out.println("Error al seleccionar todas las ciudades");
					transaction.rollback();
			}
		}
		return ciudades;
	}

	public List<Ciudad> selectCiudadesMillon() {
		Transaction transaction=null;
		List<Ciudad> ciudades=null;
		Ciudad c=null;
		try (Session session=HibernateUtil.getSessionFactory().openSession()){
			transaction=session.beginTransaction();
			ciudades=session.createQuery("from Ciudad where habs > 1000000",Ciudad.class).getResultList();
			transaction.commit();
		} catch (Exception e) {
			if (transaction!=null) {
					System.out.println("Error al seleccionar las ciudades");
					transaction.rollback();
			}
		}
		return ciudades;
	}
	
	public List<Ciudad> selectCiudadesMenos(long n) {
		Transaction transaction=null;
		List<Ciudad> ciudades=null;
		Ciudad c=null;
		try (Session session=HibernateUtil.getSessionFactory().openSession()){
			transaction=session.beginTransaction();
			Query<Ciudad> query=session.createQuery("from Ciudad where habs < :numHabs",Ciudad.class);
			query.setParameter("numHabs", n);
			ciudades=query.getResultList();
			transaction.commit();
		} catch (Exception e) {
			if (transaction!=null) {
					System.out.println("Error al seleccionar las ciudades");
					transaction.rollback();
			}
		}
		return ciudades;
	}
	
	public Ciudad selectCiudadByName(String nom) {
		Transaction transaction=null;
		Ciudad c=null;
		try (Session session=HibernateUtil.getSessionFactory().openSession()){
			transaction=session.beginTransaction();
			Query<Ciudad> query=session.createQuery("from Ciudad where nom = :nombreCiudad",Ciudad.class);
			query.setParameter("nombreCiudad", nom);
			c=query.uniqueResult();
			transaction.commit();
		} catch (Exception e) {
			if (transaction!=null) {
				System.out.println("Error al seleccionar ciudad");
				transaction.rollback();
			}
		}
		return c;
	}

}

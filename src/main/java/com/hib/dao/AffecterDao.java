package com.hib.dao;

import com.hib.model.Affecter;
import com.hib.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AffecterDao {

    // Ajouter une affectation
	public void saveAffectation(Affecter affecter) {
	    Transaction transaction = null;
	    Session session = null;
	    try {
	        session = HibernateUtil.getSessionFactory().openSession();
	        transaction = session.beginTransaction();

	        if (affecter.getEmploye() == null || affecter.getLieu() == null) {
	            throw new IllegalArgumentException("Employé et lieu ne peuvent pas être null !");
	        }

	        session.persist(affecter);
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        if (session != null && session.isOpen()) {
	            session.close();
	        }
	    }
	}


    

    // Obtenir une affectation par ID
    public Affecter getAffectationById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Affecter.class, id);
        }
    }

    // Obtenir toutes les affectations
    @SuppressWarnings({ "unchecked", "deprecation" })
    public List<Affecter> getAllAffectations() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Affecter").list();
        }
    }

    // Mettre à jour une affectation
    public void updateAffectation(Affecter affecter) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            // Vérifie si l'affectation existe
            Affecter existingAffecter = session.get(Affecter.class, affecter.getCodeaffecter());
            if (existingAffecter != null) {
                // Si employe et lieu sont modifiés, assure-toi qu'ils sont valides
                if (affecter.getEmploye() != null && affecter.getLieu() != null) {
                    existingAffecter.setEmploye(affecter.getEmploye());
                    existingAffecter.setLieu(affecter.getLieu());
                }
                existingAffecter.setDate(affecter.getDate());
                
                // Mise à jour dans la base de données
                session.merge(existingAffecter);
                transaction.commit();
            } else {
                System.out.println("Affectation non trouvée pour l'ID: " + affecter.getCodeaffecter());
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }


    // Supprimer une affectation
    public void deleteAffectation(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Affecter affecter = session.get(Affecter.class, id);
            if (affecter != null) {
                session.remove(affecter);
                System.out.println("Affectation supprimée avec succès !");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}

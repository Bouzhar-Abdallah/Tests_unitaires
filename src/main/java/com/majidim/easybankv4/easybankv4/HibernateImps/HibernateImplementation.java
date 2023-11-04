package com.majidim.easybankv4.easybankv4.HibernateImps;

import com.majidim.easybankv4.easybankv4.HibernateImps.Interfaces.InterfaceData;
import jakarta.persistence.*;
import com.majidim.easybankv4.easybankv4.dto.*;
import com.majidim.easybankv4.easybankv4.dto.Employe;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class HibernateImplementation<Entity, Identifier> implements InterfaceData<Entity, Identifier> {
    private final EntityManagerFactory emf;

    public HibernateImplementation() {

        emf = Persistence.createEntityManagerFactory("myPersistence");
    }


    @Override
    public Optional<Entity> create(Entity entity) {
        System.out.println("create");
            EntityManager em = emf.createEntityManager(); //represent the context
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            return Optional.of(entity);
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
            return Optional.empty(); // Return an empty Optional on error
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public Optional<Entity> update(Entity entity) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            Entity updatedEntity = em.merge(entity);
            transaction.commit();
            return Optional.of(updatedEntity);
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return Optional.empty();
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }


    @Override
    public Optional<Entity> findByID(Identifier id, Class<Entity> entityClass) {

        EntityManager em = emf.createEntityManager(); //represent the context
        try {
            em.getTransaction().begin();
            try {
                Entity entity = em.find(entityClass, id);
                return Optional.ofNullable(entity);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            em.getTransaction().commit();
            return Optional.empty();
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }


    @Override
    public List<Entity> getAll(Class<Entity> entityClass) {
        EntityManager em = emf.createEntityManager(); //represent the context
        try {

            em.getTransaction().begin();

            // Use JPQL (Java Persistence Query Language) to create a query to select all entities
            String jpql = "SELECT e FROM " + entityClass.getSimpleName() + " e";

            TypedQuery<Entity> query = em.createQuery(jpql, entityClass);


            List<Entity> resultList = query.getResultList();

            em.getTransaction().commit();

            return resultList;
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
            return Collections.emptyList(); // Return an empty list on error
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public boolean delete(Identifier id, Class<Entity> entityClass) {
        System.out.println("delete");
        System.out.println("id "+id);
        System.out.println("entity class "+entityClass);
        EntityManager em = emf.createEntityManager(); //represent the context
        try {
            em.getTransaction().begin();
            try {
                Entity entity = em.find(entityClass, id);
                if (entity != null) {
                    System.out.println("found entity");
                    em.remove(entity);

                    em.getTransaction().commit();
                    return true;
                } else {
                    return false;
                }
            } catch (EntityNotFoundException e) {
                System.out.println("couldn't find entity");
                System.out.println(e.getMessage());
                return false; // Entity not found
            } catch (Exception e) {
                //e.printStackTrace();
                return false;
            } finally {
                if (em.isOpen()) {
                    em.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}

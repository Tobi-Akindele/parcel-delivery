package com.tobiakindele.parceldelivery.persistence.dao.impl;

import com.tobiakindele.parceldelivery.persistence.JPAResourceBean;
import com.tobiakindele.parceldelivery.models.User;
import com.tobiakindele.parceldelivery.persistence.dao.UserDao;
import com.tobiakindele.parceldelivery.utils.LoggerUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;


/**
 *
 * @author oyindamolaakindele
 */

public class UserDaoImpl implements UserDao {
    
    private static final Logger logger = Logger.getLogger(UserDaoImpl.class.getName());
    
    private static final EntityManagerFactory emf = JPAResourceBean.getEMF();
    
    private static final String FIND_BY_EMAIL = "SELECT u FROM Users u WHERE u.email = :email";

    @Override
    public User findByEmail(String email) {
        final EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(FIND_BY_EMAIL, User.class).setParameter("email", email).getSingleResult();
        } catch (NoResultException e) {
            LoggerUtil.logError(logger, Level.INFO, e);
            return null;
        } catch (Exception e) {
            LoggerUtil.logError(logger, Level.SEVERE, e);
            throw e;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public User save(User user) {
        final EntityManager em = emf.createEntityManager();
        try {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            em.persist(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            LoggerUtil.logError(logger, Level.SEVERE, e);
            throw e;
        } finally {
            if (em != null) {
                em.close();
            }
        }

        return user;
    }

    @Override
    public User read(Long id) {
        final EntityManager em = emf.createEntityManager();
        try {
            return em.find(User.class, id);
        } catch (Exception e) {
            LoggerUtil.logError(logger, Level.SEVERE, e);
            throw e;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public User update(User t) {
        return null;
    }

    @Override
    public void delete(Long id) {
        
    }
}

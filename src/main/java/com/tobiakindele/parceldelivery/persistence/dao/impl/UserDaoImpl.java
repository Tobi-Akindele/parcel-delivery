package com.tobiakindele.parceldelivery.persistence.dao.impl;

import com.tobiakindele.parceldelivery.persistence.JPAResourceBean;
import com.tobiakindele.parceldelivery.models.User;
import com.tobiakindele.parceldelivery.persistence.dao.UserDao;
import com.tobiakindele.parceldelivery.utils.LoggerUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;


/**
 *
 * @author oyindamolaakindele
 */

public class UserDaoImpl implements UserDao {
    
    private static final Logger logger = Logger.getLogger(UserDaoImpl.class.getName());
    
    protected JPAResourceBean jPAResourceBean = new JPAResourceBean();
    
    private static final String FIND_BY_EMAIL = "SELECT u FROM Users u WHERE u.email = :email";

    @Override
    public User findByEmail(String email) {
        EntityManager em = jPAResourceBean.getEMF().createEntityManager();
        try {
            return em.createQuery(FIND_BY_EMAIL, User.class).setParameter("email", email).getSingleResult();
        } catch(Exception e){
            LoggerUtil.logError(logger, Level.SEVERE, e);
            return null;
        }
        finally {
            em.close();
        }
    }

    @Override
    public User save(User user) {
        EntityManager em = jPAResourceBean.getEMF().createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } finally{
            em.close();
        }
        return user;
    }

    @Override
    public User read(Long id) {
        EntityManager em = jPAResourceBean.getEMF().createEntityManager();
        try {
            return em.find(User.class, id);
        } finally {
            em.close();
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

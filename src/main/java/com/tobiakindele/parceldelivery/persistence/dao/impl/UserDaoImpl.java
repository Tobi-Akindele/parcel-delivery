package com.tobiakindele.parceldelivery.persistence.dao.impl;

import com.tobiakindele.parceldelivery.persistence.JPAResourceBean;
import com.tobiakindele.parceldelivery.models.User;
import com.tobiakindele.parceldelivery.persistence.dao.UserDao;
import javax.persistence.EntityManager;


/**
 *
 * @author oyindamolaakindele
 */

public class UserDaoImpl implements UserDao {
    
    protected JPAResourceBean jPAResourceBean;
    
    @Override
    public User save(User user){
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
}

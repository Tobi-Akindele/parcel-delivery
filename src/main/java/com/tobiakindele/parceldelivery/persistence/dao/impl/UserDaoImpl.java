package com.tobiakindele.parceldelivery.persistence.dao.impl;

import com.tobiakindele.parceldelivery.dto.UserDto;
import com.tobiakindele.parceldelivery.mapper.ObjectMapper;
import com.tobiakindele.parceldelivery.persistence.JPAResourceBean;
import com.tobiakindele.parceldelivery.models.User;
import com.tobiakindele.parceldelivery.persistence.dao.UserDao;
import com.tobiakindele.parceldelivery.utils.LoggerUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import org.dozer.Mapper;
import org.dozer.MappingException;


/**
 *
 * @author oyindamolaakindele
 */

public class UserDaoImpl implements UserDao {
    
    private static final Logger logger = Logger.getLogger(UserDaoImpl.class.getName());
    
    private static final EntityManagerFactory emf = JPAResourceBean.getEMF();
    
    private static final Mapper mapper = ObjectMapper.getMapper();
    
    private static final String FIND_BY_EMAIL = "SELECT u FROM Users u WHERE u.email = :email";
    
    private static final String FIND_BY_VERIFICATION_CODE = "SELECT u FROM Users u WHERE u.verificationCode = :verificationCode";

    @Override
    public UserDto findByEmail(String email) {
        final EntityManager em = emf.createEntityManager();
        try {
            return mapper.map(em.createQuery(FIND_BY_EMAIL, User.class)
                    .setParameter("email", email).getSingleResult(), UserDto.class);
        } catch (NoResultException e) {
            LoggerUtil.logError(logger, Level.INFO, e);
            return null;
        } catch (MappingException e) {
            LoggerUtil.logError(logger, Level.SEVERE, e);
            throw e;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    @Override
    public UserDto findByVerificationCode(String verificationCode) {
        final EntityManager em = emf.createEntityManager();
        try {
            return mapper.map(em.createQuery(FIND_BY_VERIFICATION_CODE, User.class)
                    .setParameter("verificationCode", verificationCode).getSingleResult(), UserDto.class);
        } catch (NoResultException e) {
            LoggerUtil.logError(logger, Level.INFO, e);
            return null;
        } catch (MappingException e) {
            LoggerUtil.logError(logger, Level.SEVERE, e);
            throw e;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public UserDto save(User user) {
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
        return mapper.map(user, UserDto.class);
    }

    @Override
    public UserDto read(Long id) {
        final EntityManager em = emf.createEntityManager();
        try {
            return mapper.map(em.find(User.class, id), UserDto.class);
        } catch (MappingException e) {
            LoggerUtil.logError(logger, Level.SEVERE, e);
            throw e;
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
    public UserDto update(User user) {
        final EntityManager em = emf.createEntityManager();
        try {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            em.merge(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            LoggerUtil.logError(logger, Level.SEVERE, e);
            throw e;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return mapper.map(user, UserDto.class);
    }

    @Override
    public void delete(Long id) {
        
    }
}

package com.tobiakindele.parceldelivery.persistence.dao.impl;

import com.tobiakindele.parceldelivery.dto.ParcelDeliveryDto;
import com.tobiakindele.parceldelivery.mapper.ObjectMapper;
import com.tobiakindele.parceldelivery.models.ParcelDelivery;
import com.tobiakindele.parceldelivery.persistence.JPAResourceBean;
import com.tobiakindele.parceldelivery.persistence.dao.ParcelDeliveryDao;
import com.tobiakindele.parceldelivery.utils.LoggerUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.dozer.Mapper;
import org.dozer.MappingException;

/**
 *
 * @author oyindamolaakindele
 */
public class ParcelDeliveryDaoImpl implements ParcelDeliveryDao {

    private static final Logger logger = Logger.getLogger(ParcelDeliveryDaoImpl.class.getName());
    
    private static final EntityManagerFactory emf = JPAResourceBean.getEMF();
    
    private static final Mapper mapper = ObjectMapper.getMapper();
    
    private static final String FIND_BY_STATUS = "SELECT p FROM ParcelDelivery p WHERE (p.status = :status OR :status IS NULL)"
            + " AND p.createdBy = :userId ORDER BY p.id DESC";
    private static final String FIND_BY_STATUS_COUNT = "SELECT COUNT(p.id) FROM ParcelDelivery p WHERE (p.status = :status OR :status IS NULL)"
            + " AND p.createdBy = :userId ";
    
//    private static final String FIND_BY_STATUS = "SELECT p FROM ParcelDelivery p ";
//    private static final String FIND_BY_STATUS_COUNT = "SELECT COUNT(p.id) FROM ParcelDelivery p ";

    @Override
    public ParcelDeliveryDto save(ParcelDelivery parcelDelivery) {
        final EntityManager em = emf.createEntityManager();
        try {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            em.persist(parcelDelivery);
            em.getTransaction().commit();
        } catch (Exception e) {
            LoggerUtil.logError(logger, Level.SEVERE, e);
            throw e;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return mapper.map(parcelDelivery, ParcelDeliveryDto.class);
    }

    @Override
    public ParcelDeliveryDto read(Long id) {
        final EntityManager em = emf.createEntityManager();
        try {
            return mapper.map(em.find(ParcelDelivery.class, id), ParcelDeliveryDto.class);
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
    public ParcelDeliveryDto update(ParcelDelivery parcelDelivery) {
        final EntityManager em = emf.createEntityManager();
        try {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            em.merge(parcelDelivery);
            em.getTransaction().commit();
        } catch (Exception e) {
            LoggerUtil.logError(logger, Level.SEVERE, e);
            throw e;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return mapper.map(parcelDelivery, ParcelDeliveryDto.class);
    }

    @Override
    public void delete(Long id) {
    }

    @Override
    public List<ParcelDeliveryDto> getAllParcelDeliveryByStatus(String status, Long userId, int []range) {
        final EntityManager em = emf.createEntityManager();
        try {
            List<ParcelDelivery> parcelList = em.createQuery(FIND_BY_STATUS, ParcelDelivery.class)
                    .setParameter("status", status)
                    .setParameter("userId", userId)
                    .setMaxResults(range[1] - range[0])
                    .setFirstResult(range[0])
                    .getResultList();
            
            List<ParcelDeliveryDto> result = new ArrayList<>();
            if(parcelList != null && !parcelList.isEmpty()){
                parcelList.forEach(parcel -> {
                    result.add(mapper.map(parcel, ParcelDeliveryDto.class));
                });
            }
            return result;
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
    public Long getAllParcelDeliveryByStatusCount(String status, Long userId) {
        final EntityManager em = emf.createEntityManager();
        try {
            Long noOfResults = (Long) em.createQuery(FIND_BY_STATUS_COUNT)
                    .setParameter("status", status)
                    .setParameter("userId", userId)
                    .getSingleResult();
            
            return noOfResults;
        } catch (MappingException e) {
            LoggerUtil.logError(logger, Level.SEVERE, e);
            throw e;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
}

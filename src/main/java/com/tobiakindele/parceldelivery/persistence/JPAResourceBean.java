package com.tobiakindele.parceldelivery.persistence;

import com.tobiakindele.parceldelivery.utils.ConstantUtils;
import java.util.HashMap;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author oyindamolaakindele
 */

public class JPAResourceBean {

    protected static EntityManagerFactory entityManagerFactory;

    public static EntityManagerFactory getEMF() {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory(ConstantUtils.PERSIST_UNIT, new HashMap<>());
        }
        return entityManagerFactory;
    }
}

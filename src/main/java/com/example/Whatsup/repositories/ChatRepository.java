package com.example.Whatsup.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class ChatRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Boolean createNewChat(Integer userOne, Integer userTwo){
        StoredProcedureQuery spq = this.entityManager.createNamedStoredProcedureQuery("createNewChat");
        spq.setParameter("userOne", userOne);
        spq.setParameter("userTwo", userTwo);

        return spq.execute();
    }
}

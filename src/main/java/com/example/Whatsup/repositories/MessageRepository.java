package com.example.Whatsup.repositories;

import com.example.Whatsup.entities.Message;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureParameter;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MessageRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<Message> getUserMessages(Integer userId){
        StoredProcedureQuery spq = this.entityManager.createNamedStoredProcedureQuery("getUserMessages");
        spq.setParameter("user_id", userId);

        return spq.getResultList();
    }

    @Transactional
    public Boolean insertUserMessage(Message message, Integer userId ){
        StoredProcedureQuery spq = this.entityManager.createNamedStoredProcedureQuery("insertUserMessage");
        spq.setParameter("user_id", userId);
        spq.setParameter("message", message.getContent());

        return spq.execute();
    }
}

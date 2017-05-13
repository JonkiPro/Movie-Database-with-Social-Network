package com.jonki.DAO;

import com.jonki.Entity.Message;
import com.jonki.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
@Transactional
public interface MessageCRUDRepository extends JpaRepository<Message, Long> {

    @Modifying(clearAutomatically = true)
    @Query("update Message e set e.dateOfRead = :dateOfRead where e.id = :id")
    public void setDateOfRead(@Param("id") Long id, @Param("dateOfRead") Date dateOfRead);

    @Modifying(clearAutomatically = true)
    @Query("update Message e set e.isVisibleForRecipient = :isVisibleForRecipient where e.id = :id")
    public void deleteReceivedMessage(@Param("id") Long id, @Param("isVisibleForRecipient") boolean isVisibleForRecipient);

    @Modifying(clearAutomatically = true)
    @Query("update Message e set e.isVisibleForSender = :isVisibleForSender where e.id = :id")
    public void deleteSentMessage(@Param("id") Long id, @Param("isVisibleForSender") boolean isVisibleForSender);

    @Modifying(clearAutomatically = true)
    @Transactional(readOnly = true)
    @Query("select e from Message e where e.isVisibleForRecipient = :isVisibleForRecipient and e.recipientUser = :recipient")
    public List<Message> findAllReceivedMessages(@Param("isVisibleForRecipient") boolean isVisibleForRecipient, @Param("recipient") User recipient);

    @Modifying(clearAutomatically = true)
    @Transactional(readOnly = true)
    @Query("select e from Message e where e.isVisibleForSender = :isVisibleForSender and e.senderUser = :sender")
    public List<Message> findAllSentMessages(@Param("isVisibleForSender") boolean isVisibleForSender, @Param("sender") User sender);
}

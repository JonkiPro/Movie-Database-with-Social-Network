package com.jonki.DAO;

import com.jonki.Entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
@Transactional
public interface MessageCRUDRepository extends JpaRepository<Message, Long> {

    @Modifying(clearAutomatically = true)
    @Query("update Message e set e.dateOfRead = :dateOfRead where e.id = :id")
    public void setDateOfRead(@Param("id") Long id, @Param("dateOfRead") Date dateOfRead);
}

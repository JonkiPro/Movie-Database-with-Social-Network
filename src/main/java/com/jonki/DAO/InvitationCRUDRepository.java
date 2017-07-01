package com.jonki.DAO;

import com.jonki.Entity.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface InvitationCRUDRepository extends JpaRepository<Invitation, Long> {

    @Transactional(readOnly = true)
    public Invitation findOneByFromIDAndToID(Long fromID, Long toID);

    @Transactional(readOnly = true)
    public List<Invitation> findByFromID(Long fromID);

    @Transactional(readOnly = true)
    public List<Invitation> findByToID(Long toID);
}

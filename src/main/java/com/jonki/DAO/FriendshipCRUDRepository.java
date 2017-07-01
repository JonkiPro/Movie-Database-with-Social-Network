package com.jonki.DAO;

import com.jonki.Entity.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface FriendshipCRUDRepository extends JpaRepository<Friendship, Long> {

    @Transactional(readOnly = true)
    public Friendship findOneByFriendAAndFriendB(Long friendA, Long friendB);

    @Transactional(readOnly = true)
    public List<Friendship> findByFriendA(Long friendA);
}

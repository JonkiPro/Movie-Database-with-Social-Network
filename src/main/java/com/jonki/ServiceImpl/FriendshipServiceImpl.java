package com.jonki.ServiceImpl;

import com.jonki.DAO.FriendshipCRUDRepository;
import com.jonki.Entity.Friendship;
import com.jonki.Service.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("friendshipService")
public class FriendshipServiceImpl implements FriendshipService {

    private FriendshipCRUDRepository friendshipCRUDRepository;

    @Autowired
    public FriendshipServiceImpl(FriendshipCRUDRepository friendshipCRUDRepository) {
        this.friendshipCRUDRepository = friendshipCRUDRepository;
    }

    @Override
    public void addFriend(final Long friendA_ID, final Long friendB_ID) {
        friendshipCRUDRepository.save(new Friendship(friendA_ID, friendB_ID));
        friendshipCRUDRepository.save(new Friendship(friendB_ID, friendA_ID));
    }

    @Override
    public void deleteFriend(final Long friendA_ID, final Long friendB_ID) {
        friendshipCRUDRepository.delete(friendshipCRUDRepository.findOneByFriendAAndFriendB(friendA_ID, friendB_ID));
        friendshipCRUDRepository.delete(friendshipCRUDRepository.findOneByFriendAAndFriendB(friendB_ID, friendA_ID));
    }

    @Override
    public List<Friendship> findFriendship(final Long id) {
        return friendshipCRUDRepository.findByFriendA(id);
    }
}

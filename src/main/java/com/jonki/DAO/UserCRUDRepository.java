package com.jonki.DAO;

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
public interface UserCRUDRepository extends JpaRepository<User, Long> {

    @Modifying(clearAutomatically = true)
    @Query("update User e set e.name = :name where e.id = :id")
    public void setName(@Param("id") Long id, @Param("name") String name);

    @Modifying(clearAutomatically = true)
    @Query("update User e set e.secondName = :secondName where e.id = :id")
    public void setSecondName(@Param("id") Long id, @Param("secondName") String secondName);

    @Modifying(clearAutomatically = true)
    @Query("update User e set e.lastName = :lastName where e.id = :id")
    public void setLastName(@Param("id") Long id, @Param("lastName") String lastName);

    @Modifying(clearAutomatically = true)
    @Query("update User e set e.sex = :sex where e.id = :id")
    public void setSex(@Param("id") Long id, @Param("sex") String sex);

    @Modifying(clearAutomatically = true)
    @Query("update User e set e.password = :password where e.id = :id")
    public void setPassword(@Param("id") Long id, @Param("password") String password);

    @Modifying(clearAutomatically = true)
    @Query("update User e set e.email = :email where e.id = :id")
    public void setEmail(@Param("id") Long id, @Param("email") String email);

    @Modifying(clearAutomatically = true)
    @Query("update User e set e.newEmail = :newEmail where e.id = :id")
    public void setNewEmail(@Param("id") Long id, @Param("newEmail") String newEmail);

    @Modifying(clearAutomatically = true)
    @Query("update User e set e.codeChange = :codeChange where e.id = :id")
    public void setCodeChange(@Param("id") Long id, @Param("codeChange") int codeChange);

    @Modifying(clearAutomatically = true)
    @Query("update User e set e.activationCode = :activationCode where e.id = :id")
    public void setActivationCode(@Param("id") Long id, @Param("activationCode") int activationCode);

    @Modifying(clearAutomatically = true)
    @Query("update User e set e.urlAvatar = :urlAvatar where e.id = :id")
    public void setAvatar(@Param("id") Long id, @Param("urlAvatar") String urlAvatar);

    @Modifying(clearAutomatically = true)
    @Query("update User e set e.updateDate = :updateDate where e.id = :id")
    public void setUpdateDate(@Param("id") Long id, @Param("updateDate") Date updateDate);

    @Modifying(clearAutomatically = true)
    @Transactional(readOnly = true)
    @Query("select e from User e where e.username LIKE :username")
    public List<User> findUsersByUsernamePhrase(@Param("username") String username);

    @Modifying(clearAutomatically = true)
    @Transactional(readOnly = true)
    @Query("select e from User e where e.email LIKE :email")
    public List<User> findUsersByEmailPhrase(@Param("email") String email);

    @Transactional(readOnly = true)
    public User findOneByUsername(String username);

    @Modifying(clearAutomatically = true)
    @Query("update User e set e.activation = :activation, e.activationCode = :activationCode  where e.id = :id")
    public void setActivationUser(@Param("id") Long id, @Param("activation") boolean activation, @Param("activationCode") int activationCode);

    @Modifying(clearAutomatically = true)
    @Query("update User e set e.password = :password where e.id = :id")
    public void resetPassword(@Param("id") Long id, @Param("password") String password);
}

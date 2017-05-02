package com.jonki.DAO;

import com.jonki.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Repository
@Transactional
public interface GenericDAO<T extends User, ID extends Serializable> extends JpaRepository<T, ID> {}

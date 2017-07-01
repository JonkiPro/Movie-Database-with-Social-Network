package com.jonki.Entity;

import javax.persistence.*;

@Entity
@Table(name = "friendship")
public class Friendship {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "friendA")
    private Long friendA;

    @Column(name = "friendB")
    private Long friendB;

    public Friendship() {}

    public Friendship(Long friendA_ID, Long friendB_ID) {
        this.friendA = friendA_ID;
        this.friendB = friendB_ID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFriendA() {
        return friendA;
    }

    public void setFriendA(Long friendA) {
        this.friendA = friendA;
    }

    public Long getFriendB() {
        return friendB;
    }

    public void setFriendB(Long friendB) {
        this.friendB = friendB;
    }
}

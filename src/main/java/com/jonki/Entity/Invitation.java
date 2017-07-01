package com.jonki.Entity;

import javax.persistence.*;

@Entity
@Table(name = "invitations")
public class Invitation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "fromID")
    private Long fromID;

    @Column(name = "toID")
    private Long toID;

    public Invitation() {}

    public Invitation(Long fromID, Long toID) {
        this.fromID = fromID;
        this.toID = toID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFromID() {
        return fromID;
    }

    public void setFromID(Long fromID) {
        this.fromID = fromID;
    }

    public Long getToID() {
        return toID;
    }

    public void setToID(Long toID) {
        this.toID = toID;
    }
}

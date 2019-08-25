package com.bolsadeideas.springboot.backend.apirest.entities;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * This class represents the <b>Client</b> table.
 * @æuthor Ernesto A. Rodriguez Acosta
 */
@Entity
@Table(name = "clients")
@Data
@NoArgsConstructor
@Builder
public class Client implements Serializable {

    private static final long serialVersionUID = -8778960327754176386L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    private String email;

    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    private Date createdAt;

    public Client(Long id, String name, String lastName, String email, Date createdAt) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.createdAt = createdAt;
    }
}

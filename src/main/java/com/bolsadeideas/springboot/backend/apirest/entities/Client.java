package com.bolsadeideas.springboot.backend.apirest.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "All details about the Client")
public class Client implements Serializable {

    private static final long serialVersionUID = -8778960327754176386L;

    @ApiModelProperty(notes = "The database generated client ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(notes = "The client first name")
    private String name;

    @ApiModelProperty(notes = "The client last name")
    private String lastName;

    @ApiModelProperty(notes = "The client email")
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

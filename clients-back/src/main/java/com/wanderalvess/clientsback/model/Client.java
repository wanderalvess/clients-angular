package com.wanderalvess.clientsback.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import  lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "cliente")
public class Client {

    @Id
    @JsonProperty("_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome",length = 100, nullable = false)
    private String name;

    @Column(name = "documento", length = 25, nullable = false)
    private String document;

    @Column(name = "endereço",length = 100, nullable = false)
    private String address;

    @Column(name = "Bairro", nullable = false)
    private String neighborhood;

    @Column(length = 60, nullable = false)
    private String latitude;

    @Column(length = 60, nullable = false)
    private String longitude;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
    private List<Phone> phones;



}

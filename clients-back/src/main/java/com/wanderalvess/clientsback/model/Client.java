package com.wanderalvess.clientsback.model;
import  lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Data
@Table(name = "cliente")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo",length = 100, nullable = false)
    private String code;

    @Column(name = "nome",length = 100, nullable = false)
    private String name;

    @Column(name = "documento", length = 20, nullable = false)
    private String document;

    @Column(length = 50, nullable = false)
    private String latitude;

    @Column(length = 50, nullable = false)
    private String longitude;



}

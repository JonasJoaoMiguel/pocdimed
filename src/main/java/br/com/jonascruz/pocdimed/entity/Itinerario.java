package br.com.jonascruz.pocdimed.entity;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "ITINERARIO")
public class Itinerario implements Serializable {

    @Id
    @Column(name = "ID")
    private Long idlinha;

    @Column(name = "CODIGO")
    private String codigo;

    @Column(name = "NOME")
    private String nome;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iditinerario")
    //@ElementCollection(targetClass=CoordenadaGeografica.class)
    private List<CoordenadaGeografica> coordenadaGeograficaList;

}

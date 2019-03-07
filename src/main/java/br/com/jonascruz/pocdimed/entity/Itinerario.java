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
@SequenceGenerator(name = "S_ITINERARIO", sequenceName = "S_ITINERARIO", allocationSize = 1, initialValue = 1)
public class Itinerario implements Serializable {

    @Id
    @GeneratedValue(generator = "S_ITINERARIO", strategy = GenerationType.SEQUENCE)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "ID_LINHA", nullable = false)
    private Long idlinha;

    @Column(name = "CODIGO")
    private String codigo;

    @Column(name = "NOME")
    private String nome;

    //@NotNull
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idItinerario")
    @ElementCollection(targetClass=CoordenadaGeografica.class)
    private List<CoordenadaGeografica> coordenadaGeograficaList;

}

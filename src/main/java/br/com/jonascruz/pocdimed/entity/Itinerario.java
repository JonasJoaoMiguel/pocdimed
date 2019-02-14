package br.com.jonascruz.pocdimed.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ITINERARIO")
public class Itinerario implements Serializable {


    @Id
    @Column(name = "ID", nullable = false)
    private Long idlinha;

    @Column(name = "CODIGO")
    private String codigo;

    @Column(name = "NOME")
    private String nome;

    @NotNull
    @JoinColumn(name = "ID_COORDENADAGEOGRAFICA", referencedColumnName = "ID")
    @ManyToOne
    private CoordenadaGeografica coordenadaGeografica;

}

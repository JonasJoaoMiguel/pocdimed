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
public class Itinerario implements Serializable {


    @Id
    @Column(name = "ID", nullable = false)
    private Long idlinha;

    private String codigo;

    private String nome;

    @NotNull
    @JoinColumn(name = "ID_COORDENADAGEOGRAFICA", referencedColumnName = "ID")
    @ManyToOne
    private CoordenadaGeografica coordenadaGeografica;

}

package br.com.jonascruz.pocdimed.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "COORDENADA_GEOGRAFICA")
public class CoordenadaGeografica implements Serializable {

    @Id
    @Column(name = "ID")
    private Long idItinerario;

    @Column(name = "LAT")
    private double lat;

    @Column(name = "LNG")
    private double lng;

    @NotNull
    @JoinColumn(name = "ID_ITINERARIO", referencedColumnName = "ID")
    @OneToOne
    private Itinerario itinerario;
}

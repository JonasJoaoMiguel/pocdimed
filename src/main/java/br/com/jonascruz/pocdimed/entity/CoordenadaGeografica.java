package br.com.jonascruz.pocdimed.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "COORDENADA_GEOGRAFICA")
public class CoordenadaGeografica implements Serializable {

    @Id
    @Column(name = "ID")
    private Long id;

    //@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_ITINERARIO", referencedColumnName = "ID")
    private Long iditinerario;

    @Column(name = "LAT")
    private double lat;

    @Column(name = "LNG")
    private double lng;

}

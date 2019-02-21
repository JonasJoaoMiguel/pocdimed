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
@SequenceGenerator(name = "S_COORDENADA_GEOGRAFICA", sequenceName = "S_COORDENADA_GEOGRAFICA", allocationSize = 1, initialValue = 1)
public class CoordenadaGeografica implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "S_COORDENADA_GEOGRAFICA", strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "LAT")
    private double lat;

    @Column(name = "LNG")
    private double lng;

}

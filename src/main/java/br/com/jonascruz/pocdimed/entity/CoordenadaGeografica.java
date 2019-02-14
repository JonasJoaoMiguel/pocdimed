package br.com.jonascruz.pocdimed.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Builder
@AllArgsConstructor
@Entity
public class CoordenadaGeografica {

    @Column(name = "LAT")
    private String lat;

    @Column(name = "LNG")
    private String lng;
}

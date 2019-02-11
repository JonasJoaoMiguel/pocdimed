package br.com.jonascruz.pocdimed.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@Entity
public class CoordenadaGeografica implements Serializable {


    @Id
    private Long id;
    private String lat;
    private String lng;
}

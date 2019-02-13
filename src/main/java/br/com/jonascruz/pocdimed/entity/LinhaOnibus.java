package br.com.jonascruz.pocdimed.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "LINHA_ONIBUS")
public class LinhaOnibus implements Serializable {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "CODIGO")
    private String codigo;

    @Column(name = "NOME")
    private String nome;

}

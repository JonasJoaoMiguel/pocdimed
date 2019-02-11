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
public class Cliente implements Serializable {

    @Id
    @SequenceGenerator(name = "S_CLIENTE", sequenceName = "S_CLIENTE", allocationSize = 1)
    @GeneratedValue(generator = "S_CLIENTE", strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nome;

    private int cpf;

    @JoinColumn(name = "ID_LISTALINHAS", referencedColumnName = "ID")
    @ManyToOne
    private LinhaOnibus listaLinhas;

}

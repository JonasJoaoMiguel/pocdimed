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
@Table(name = "CLIENTE")
@SequenceGenerator(name = "S_CLIENTE", sequenceName = "S_CLIENTE", allocationSize = 1, initialValue = 1)
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(generator = "S_CLIENTE", strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "CPF")
    private Long cpf;

   // @JoinColumn(name = "ID_LISTALINHAS", referencedColumnName = "ID")
   // @ManyToOne
   // private LinhaOnibus linhas;

}

package br.com.jonascruz.pocdimed.entity;


import br.com.jonascruz.pocdimed.service.CoordenadaGeograficaService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

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
//    --ta faltando coisa aqui
//            --entidade no pode ter autowired
//    segue corrigindo esses probleminhas que o teste vai rodar, o teste s funciona se estiver rodando
//
//    a entidade tem que ter tudo aquilo que coloquei ali em cima
//            e essa lista aqui de baixo precisa ser um relacionamento oneToMany ou ManyToOne para uma entidade
    @NotNull
    @JoinColumn(name = "ID_COORDENADAGEOGRAFICA", referencedColumnName = "ID")
    @ManyToOne
    private CoordenadaGeografica coordenadaGeografica;

}

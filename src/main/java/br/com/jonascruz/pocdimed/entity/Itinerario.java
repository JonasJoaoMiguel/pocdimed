package br.com.jonascruz.pocdimed.entity;


import lombok.AllArgsConstructor;


import java.util.List;

@AllArgsConstructor
public class Itinerario {


    private Long idLinha;
    private String codido;
    private String nome;
    private List<CoordenadaGeografica> rotas;
}

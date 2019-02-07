package br.com.jonascruz.pocdimed.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class LinhaOnibus {

    private String id;
    private String codigo;
    private String nome;

}

package br.com.jonascruz.pocdimed.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Cliente {

    private String nome;
    private int cpf;
}

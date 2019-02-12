package br.com.jonascruz.pocdimed.stub;

import br.com.jonascruz.pocdimed.entity.Cliente;

public class ClienteStub {

    public static Cliente getCliente() {
        return Cliente.builder()
                .id(1l)
                .nome("jonas")
                .cpf(000000000)
                .build();

    }

}

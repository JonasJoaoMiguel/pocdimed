package br.com.jonascruz.pocdimed.controller;

import br.com.jonascruz.pocdimed.entity.Itinerario;
import br.com.jonascruz.pocdimed.service.ItinerarioService;
import org.springframework.beans.factory.annotation.Autowired;

public class ItinerarioRestController extends AbstractCrudRestController<Itinerario, ItinerarioService> {

    @Autowired
    private ItinerarioService itinerarioService;

    @Override
    protected ItinerarioService getService() {
        return itinerarioService;
    }
}

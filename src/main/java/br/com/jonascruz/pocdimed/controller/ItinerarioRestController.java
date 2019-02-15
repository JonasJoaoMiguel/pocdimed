package br.com.jonascruz.pocdimed.controller;

import br.com.jonascruz.pocdimed.entity.Itinerario;
import br.com.jonascruz.pocdimed.service.ItinerarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/itinerario")
public class ItinerarioRestController extends AbstractCrudRestController<Itinerario, ItinerarioService> {

    @Autowired
    private ItinerarioService itinerarioService;

    @Override
    protected ItinerarioService getService() {
        return itinerarioService;
    }
}

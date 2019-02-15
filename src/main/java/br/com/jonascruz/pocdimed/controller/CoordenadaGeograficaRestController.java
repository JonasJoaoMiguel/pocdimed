package br.com.jonascruz.pocdimed.controller;

import br.com.jonascruz.pocdimed.entity.CoordenadaGeografica;
import br.com.jonascruz.pocdimed.service.CoordenadaGeograficaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/coordenadaGeografica")
public class CoordenadaGeograficaRestController extends AbstractCrudRestController<CoordenadaGeografica, CoordenadaGeograficaService>{

    @Autowired
    private CoordenadaGeograficaService coordenadaGeograficaService;

    @Override
    protected CoordenadaGeograficaService getService() {
        return coordenadaGeograficaService;
    }
}

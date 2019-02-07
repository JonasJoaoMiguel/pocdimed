package br.com.jonascruz.pocdimed.controller;

import br.com.jonascruz.pocdimed.entity.CoordenadaGeografica;
import br.com.jonascruz.pocdimed.service.CoordenadaGeograficaService;
import org.springframework.beans.factory.annotation.Autowired;

public class CoordenadaGeograficaRestController extends AbstractCrudRestController<CoordenadaGeografica, CoordenadaGeograficaService>{

    @Autowired
    private CoordenadaGeograficaService coordenadaGeograficaService;

    @Override
    protected CoordenadaGeograficaService getService() {
        return coordenadaGeograficaService;
    }
}

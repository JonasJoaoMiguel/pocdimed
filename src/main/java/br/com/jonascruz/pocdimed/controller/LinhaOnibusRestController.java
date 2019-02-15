package br.com.jonascruz.pocdimed.controller;

import br.com.jonascruz.pocdimed.entity.LinhaOnibus;
import br.com.jonascruz.pocdimed.service.LinhaOnibusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/linhaOnibus")
public class LinhaOnibusRestController extends AbstractCrudRestController<LinhaOnibus, LinhaOnibusService> {

    @Autowired
    private LinhaOnibusService linhaOnibusService;

    @Override
    protected LinhaOnibusService getService() {
        return linhaOnibusService;
    }


}

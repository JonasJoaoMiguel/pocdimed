package br.com.jonascruz.pocdimed.service;

import br.com.jonascruz.pocdimed.entity.LinhaOnibus;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ApiDataPoaTest {


    public void findAllTest(){
        ApiDataPoa api = new ApiDataPoa();
        List<LinhaOnibus> list = api.findAll();
        assertTrue(list.size() > 0);
    }

}
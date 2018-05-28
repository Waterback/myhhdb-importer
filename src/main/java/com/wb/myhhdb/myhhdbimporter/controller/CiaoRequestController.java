package com.wb.myhhdb.myhhdbimporter.controller;

import org.apache.camel.FluentProducerTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.camel.EndpointInject;

@RestController
public class CiaoRequestController {


    //@EndpointInject(uri="geocoder:address:current")
    //private FluentProducerTemplate producer;

    @RequestMapping("/bello")
    public String ciao() {
        //String where = producer.request(String.class);
        return "Ciao Bello..";
    }


}

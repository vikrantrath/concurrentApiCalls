package com.example.webworkerdemo.controllers;
import com.example.webworkerdemo.services.BaseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/api")
public class BaseController {

    @Autowired
    private BaseServices baseServices;

    @RequestMapping(method = RequestMethod.GET,path = "/getCaseDetails")
    public String getCaseDetails() {
        return "Case Details";
    }

    @RequestMapping(method = RequestMethod.GET,path = "/getEmailDetails")
    public String getEmailDetails() {
        return "Email Details";
    }

    @RequestMapping(method = RequestMethod.GET,path = "/getCaseAndEmailDetails")
    public List<Object> getCaseAndEmailDetails()throws ExecutionException,InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Future<String> f1 = executorService.submit(baseServices.getGetCaseResults());
        Future<String> f2 = executorService.submit(baseServices.getGetEmailResults());
        List<Future<?>> tasks= new ArrayList<>();
        tasks.add(f1);
        tasks.add(f2);
        List<Object> res = tasks.parallelStream().map(e->{
            try{
              return  e.get();
            }catch(Exception ex){
                return ex;
            }
        }).collect(Collectors.toList());
        return res;
    }


}


package com.example.webworkerdemo.services;

import org.springframework.stereotype.Service;

import java.util.concurrent.Callable;

@Service
public class BaseServices {

    public Callable<String> getGetCaseResults() {
        return getCaseResults;
    }

    public Callable<String> getGetEmailResults() {
        return getEmailResults;
    }

    Callable<String> getCaseResults = ()->{
        Thread.sleep(2000);
        return "Return Case Results";
    };

    Callable<String> getEmailResults = () -> {
        // Perform some computation
        Thread.sleep(2000);
        return "Return Email Results";
    };
}

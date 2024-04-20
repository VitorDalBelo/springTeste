package com.example.demo.controllers;


import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Greeting;
import com.example.demo.converters.NumberConverter;
import com.example.demo.exceptions.UnsupportedMathOperationException;
import com.example.demo.math.SimpleMath;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@RestController()
@RequestMapping("/person")
public class GreetingController {
    private final String template = "Hello,%s";
    private final AtomicLong counter = new AtomicLong();
    private SimpleMath math = new SimpleMath();

    @RequestMapping(value = "/greeting" , method=RequestMethod.GET)
    public Greeting greeting(@RequestParam(value="name",defaultValue = "World") String name){

        return new Greeting(counter.incrementAndGet(), String.format(template,name));
        
    }

    @RequestMapping(value="/sub/{arg1}/{arg2}", method=RequestMethod.GET)
    public Double sub(@PathVariable(value = "arg1") String arg1,@PathVariable(value = "arg2") String arg2) throws UnsupportedMathOperationException {
        if( !NumberConverter.isNumeric(arg1) || !NumberConverter.isNumeric(arg2) ) throw new UnsupportedMathOperationException("Valores invalidos");
        return math.subtraction(NumberConverter.convertToDouble(arg1) , NumberConverter.convertToDouble(arg2));
    }

    @RequestMapping(value="/sum/{arg1}/{arg2}", method=RequestMethod.GET)
    public Double sum(@PathVariable(value = "arg1") String arg1,@PathVariable(value = "arg2") String arg2) throws UnsupportedMathOperationException {
        if( !NumberConverter.isNumeric(arg1) || !NumberConverter.isNumeric(arg2) ) throw new UnsupportedMathOperationException("Valores invalidos");
        return math.sum(NumberConverter.convertToDouble(arg1) , NumberConverter.convertToDouble(arg2));
    }

    @RequestMapping(value="/average/{arg1}/{arg2}", method=RequestMethod.GET)
    public Double average(@PathVariable(value = "arg1") String arg1,@PathVariable(value = "arg2") String arg2) throws UnsupportedMathOperationException {
        if( !NumberConverter.isNumeric(arg1) || !NumberConverter.isNumeric(arg2) ) throw new UnsupportedMathOperationException("Valores invalidos");
        return math.mean(NumberConverter.convertToDouble(arg1) , NumberConverter.convertToDouble(arg2));
    }
    @RequestMapping(value="/div/{arg1}/{arg2}", method=RequestMethod.GET)
    public Double div(@PathVariable(value = "arg1") String arg1,@PathVariable(value = "arg2") String arg2) throws UnsupportedMathOperationException {
        if( !NumberConverter.isNumeric(arg1) || !NumberConverter.isNumeric(arg2) ) throw new UnsupportedMathOperationException("Valores invalidos");
        if(NumberConverter.convertToDouble(arg2)==0) throw new UnsupportedMathOperationException("o segundo valor n pode ser 0");
        return math.division(NumberConverter.convertToDouble(arg1) , NumberConverter.convertToDouble(arg2));
    }
    @RequestMapping(value="/sqrt/{arg1}", method=RequestMethod.GET)
    public Double sqrt(@PathVariable(value = "arg1") String arg1) throws UnsupportedMathOperationException {
        if( !NumberConverter.isNumeric(arg1)) throw new UnsupportedMathOperationException("Valores invalidos");
        return math.squareRoot(NumberConverter.convertToDouble(arg1)) ;
    }

    
}

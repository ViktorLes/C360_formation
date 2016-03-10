package com.viseo.c360.formation.rest;


@RestController
public class HelloWorldWS {
	
	
	@RequestMapping(value = "${endpoint.helloworld}", method = RequestMethod.GET)
    @ResponseBody
    public String sayHelloWorld(){


        return "Hello World !";
    }

	
}

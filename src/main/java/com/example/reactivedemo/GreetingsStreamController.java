package com.example.reactivedemo;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class GreetingsStreamController {

    private static final List<String> greetings = Arrays.asList("Hello", "Hi","Namaskaram","Bagunnara", "Namaste", 
    "vanakkam", "Chao","Ola","Greetings","Super");
    
    @GetMapping(produces = MediaType.TEXT_PLAIN_VALUE, path="/greet")
    public Mono<String> greet(){
       
        return Mono.just("Hello");
    }


    @GetMapping(path="/all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> greetStream(){

        return Flux.interval(Duration.ofSeconds(2), Duration.ofSeconds(2)).map((Long l)->greetings.get((int)(l%10)));

    }

}

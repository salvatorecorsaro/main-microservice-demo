package com.example.mainmicroservicedemo;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class GreetingService {

    public GreetingService(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
        createClient(); // we create the client when we create the service
    }

    private WebClient client;
    final DiscoveryClient discoveryClient;

    public List<Greeting> getAllGreetings() {
        var listOfGreetings =
                client //we are using the client
                        .get() //the verb we want to use in the request -- from here
                        .uri("/greetings") // this the url that will be appended ---till here this is the request side
                        .retrieve() // open the response
                        .bodyToMono(new ParameterizedTypeReference<List<Greeting>>() {}) // map the response to a list of greetings  -- new ParameterizedTypeReference<List<Greeting is the object we are expecting to receive
                        .block(); // end the transmission

        return listOfGreetings;


    }


//    public Book save(Book book) {
//        return client
//                .post() we are creating a POST request
//                .uri("/books")
//                .body(Mono.just(book), Book.class) this is the RequestBody builder -- a Mono of just an object "book" of type Book.class
//                .retrieve()
//                .bodyToMono(Book.class)
//                .block();
//    }

//    public void delete(Long id) {
//        client
//                 .delete() delete instead of get
//                .uri("/books/" + id)
//                .retrieve()
//                .bodyToMono(Void.class) void instead of Book.class
//                .block();
//    }


    public void createClient() {
        var serviceInstanceList = discoveryClient.getInstances("crud-microservice-demo"); //we ask eureka server to give us all the instances of a specific microservice
        var clientURI = serviceInstanceList.get(0).getUri().toString(); // we are getting the url to string
        client = WebClient.create(clientURI); // we create the client with a default url to connect to
    }



}

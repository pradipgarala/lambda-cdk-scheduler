package org.example;

import java.time.LocalDateTime;

public class HelloWorldLambda {
    public void handleRequest() {
        System.out.println("Lambda invoked at: " + LocalDateTime.now());

    }
}

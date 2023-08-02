package com.mindhub.cinema.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class PaymentController {



    @GetMapping("/payment")
    public ResponseEntity<String> makeGetRequest() {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "http://localhost:9090/procesar-compra"; // Reemplaza puerto_destino con el puerto de la API destino
        String response = restTemplate.getForObject(apiUrl, String.class);

        // Aquí puedes trabajar con la respuesta recibida desde la API destino
        System.out.println("Respuesta: " + response);
        return ResponseEntity.ok("ok");
    }
}

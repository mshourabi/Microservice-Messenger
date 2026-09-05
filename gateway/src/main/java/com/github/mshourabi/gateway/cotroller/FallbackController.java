package com.github.mshourabi.gateway.cotroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/fallback")
public class FallbackController {


    @RequestMapping("/distributor")
    public Mono<ResponseEntity<Map<String, Object>>> distributorFallback() {

        Map<String, Object> body = Map.of(
                "status", 503,
                "message", "Distributor service is temporarily unavailable"
        );

        return Mono.just(
                ResponseEntity
                        .status(HttpStatus.SERVICE_UNAVAILABLE)
                        .body(body)
        );
    }
}

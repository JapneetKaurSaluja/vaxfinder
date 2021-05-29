package com.resource.vaxfinder.input;

import java.util.Scanner;
import java.util.logging.*;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;

import com.resource.vaxfinder.model.States;
import com.resource.vaxfinder.utils.AppConstants;

@Component
public class Preferences {
    
    Scanner sc=new Scanner(System.in);
    private String chosenState;
    private String chosenDistrict;

    private static final Logger logger = Logger.getLogger("Preferences");
    @PostConstruct
    public void getAllStates()
    {
        WebClient client=WebClient.builder()
                        .baseUrl(AppConstants.baseAPI)
                        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE) 
                        .build();
        Flux <States> statesList = client.get()
                                .uri("/v2/admin/location/states")
                                .retrieve()
                                .bodyToFlux(States.class);

        logger.info("Body:"+statesList);
    }

    public void getUserPreferences()
    {
        System.out.println("Enter the state in which vaccine slot is required:");
        chosenState=sc.nextLine();

    }
}

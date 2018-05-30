package com.crud.tasks.trello.client;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class TrelloClient {

    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;

    @Value("${trello.api.key}")
    private String trelloApiKey;

    @Value("${trello.api.token}")
    private String trelloApiToken;

    @Value("${trello.api.username}")
    private String username;

    @Autowired
    private RestTemplate restTemplate;

    private URI urlBuilder (){
        URI url = UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/members/ilozinski/boards" )
                .queryParam("key",trelloApiKey)
                .queryParam("token",trelloApiToken)
                .queryParam("fields","name,id")
                .queryParam("lists","all")
                .build().encode().toUri();

        return url;
    }
    //GET Request
    public List<TrelloBoardDto> getTrelloBoards() {

        Optional<TrelloBoardDto[]> boardsResponse = Optional.ofNullable(restTemplate.getForObject(urlBuilder(),TrelloBoardDto[].class));
        return Arrays.asList(boardsResponse.orElse(new TrelloBoardDto[0]));

//        if(boardsResponse != null){
//            return Arrays.asList(boardsResponse);
//        }
//        return new ArrayList<>();
    }

    //CREATE Request
    public CreatedTrelloCard createNewCard(TrelloCardDto trelloCardDto){

        URI url = UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/cards")
                .queryParam("key",trelloApiKey)
                .queryParam("token",trelloApiToken)
                .queryParam("name",trelloCardDto.getName())
                .queryParam("desc",trelloCardDto.getDescription())
                .queryParam("pos",trelloCardDto.getPos())
                .queryParam("idList",trelloCardDto.getListId())
                .queryParam("badges",trelloCardDto.getTrelloBadgesDto())
                .build().encode().toUri();

        return restTemplate.postForObject(url,null,CreatedTrelloCard.class);
    }

}

package com.crud.tasks.trello.client;

import com.crud.tasks.config.TrelloConfig;
import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class TrelloClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrelloClient.class);

    @Autowired
    private TrelloConfig trelloConfig;

    @Autowired
    private RestTemplate restTemplate;

    private URI urlBuilder (){
        URI url = UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "/members/ilozinski/boards" )
                .queryParam("key",trelloConfig.getTrelloApiKey())
                .queryParam("token",trelloConfig.getTrelloApiToken())
                .queryParam("fields","name,id")
                .queryParam("lists","all")
                .build().encode().toUri();

        return url;
    }

    //GET Request
    public List<TrelloBoardDto> getTrelloBoards() {

        try {
            Optional<TrelloBoardDto[]> boardsResponse = Optional.ofNullable(restTemplate.getForObject(urlBuilder(),TrelloBoardDto[].class));
            return Arrays.asList(boardsResponse.orElse(new TrelloBoardDto[0]));
        } catch(RestClientException e){
            LOGGER.error(e.getMessage(),e);
            return new ArrayList<>();
        }
    }

    //CREATE Request
// Do trello wyslylamy request o stworzenie nowej karty/zadania. Body tego zadania jest
// zawarte w klasie TrelloCardDto. Jak Trello wie co poszczegolne komonenty klasy TrelloCardDto
// oznaczaja? Otoz mapujemy je w Url ponizej - mowimy ze name,desc,pos,idList itd to w TrelloCardDto
// jest to i to.
// Typem zwracanym nam przez Trello jest CreatedTrelloCard
// Jest to reprezentacja danych Cards otrzymana z Trello ktora jest uzywana w naszej aplikacji

    public CreatedTrelloCard createNewCard(TrelloCardDto trelloCardDto){

        URI url = UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "/cards")
                .queryParam("key",trelloConfig.getTrelloApiKey())
                .queryParam("token",trelloConfig.getTrelloApiToken())
                .queryParam("name",trelloCardDto.getName())
                .queryParam("desc",trelloCardDto.getDescription())
                .queryParam("pos",trelloCardDto.getPos())
                .queryParam("idList",trelloCardDto.getListId())
//                .queryParam("badges",trelloCardDto.getTrelloBadgesDto())
                .build().encode().toUri();
        System.out.println(url);
        return restTemplate.postForObject(url,null,CreatedTrelloCard.class);
    }
}

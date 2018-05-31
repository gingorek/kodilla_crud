package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


//Jest to klasa ktora mapuje Cards otrzymane od API Trello
// (To co trello nam zwraca po strzeleniu metoda createNewCard)
//Jest to reprezentacja danych Cards otrzymana z Trello ktora jest uzywana w naszej aplikacji
@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatedTrelloCard {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("shortUrl")
    private String shortUrl;

//    @JsonProperty("badges")
//    private TrelloBadgesDto trelloBadgesDto;

}

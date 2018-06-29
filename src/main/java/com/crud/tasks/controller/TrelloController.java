package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.facade.TrelloFacade;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.validator.TrelloValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/trello")
public class TrelloController {


    // Tu wczesniej byla klasa Trello Client ale przez to ze chcemy jeszcze podczas tworzenia Cardow wysylas maila
    // dlatego zastosowane zostalo Trello service ktore jest opakowaniem trello client i ma funkcjonalnosc send mail.
//    @Autowired
//    private TrelloService trelloService;

    @Autowired
    private TrelloFacade trelloFacade;

    // GET request
    @RequestMapping(method = RequestMethod.GET, value = "/getTrelloBoards")
    public List<TrelloBoardDto> getTrelloBoards() {
        return trelloFacade.fetchTrelloBoards();
    }

    //CREATE request
    @RequestMapping(method = RequestMethod.POST, value = "/createTrelloCard")
    public CreatedTrelloCardDto createTrelloCard(@RequestBody TrelloCardDto trelloCardDto){
        return trelloFacade.createCard(trelloCardDto);
    }

}

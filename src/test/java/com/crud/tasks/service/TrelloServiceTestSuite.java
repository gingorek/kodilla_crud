package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTestSuite {

    @InjectMocks
    TrelloService trelloService;

    @Mock
    AdminConfig adminConfig;

    @Mock
    TrelloClient trelloClient;

    @Mock
    SimpleEmailService simpleEmailService;

    @Test
    public void testFetchingTrelloBoards(){
        //Given
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        trelloBoardDtoList.add(new TrelloBoardDto("1","First Board", new ArrayList<>()));

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoardDtoList);

        //When
        List<TrelloBoardDto> fetchedBoards = trelloService.fetchTrelloBoards();

        //Then
        assertNotNull(fetchedBoards);
    }

    @Test
    public void testCreateTrelloCard(){
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto();
        when(trelloService.createTrelloCard(trelloCardDto)).thenReturn(new CreatedTrelloCardDto());

        //When
        CreatedTrelloCardDto testCreatedTrelloCardDto = trelloService.createTrelloCard(trelloCardDto);

        //Then
        assertNotNull(testCreatedTrelloCardDto);
    }
}
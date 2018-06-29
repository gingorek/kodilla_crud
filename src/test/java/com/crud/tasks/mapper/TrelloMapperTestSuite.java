package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;



@RunWith(MockitoJUnitRunner.class)
public class TrelloMapperTestSuite {

    @InjectMocks
    private TrelloMapper trelloMapper;

    @Test
    public void testMapToListDtoMapper (){
        //Given
        TrelloList trelloList1 = new TrelloList("1", "Lista zakupow", false);
        TrelloList trelloList2 = new TrelloList("2", "Lista krajow do odwiedzenia", true);
        TrelloList trelloList3 = new TrelloList("3", "Lista pracownikow na wakacjach", false);

        List<TrelloList> testTrelloList = new ArrayList<>();
        testTrelloList.add(trelloList1);
        testTrelloList.add(trelloList2);
        testTrelloList.add(trelloList3);

        //When
        List<TrelloListDto> trelloListDtos = trelloMapper.mapToListDto(testTrelloList);

        //Then
        Assert.assertEquals(3, trelloListDtos.size());
        Assert.assertEquals("Lista krajow do odwiedzenia",trelloListDtos.get(1).getName());
    }

    @Test
    public void testMapToListMapper (){
        //Given
        TrelloListDto trelloList1 = new TrelloListDto("1", "Lista testowa zakupow", false);
        List<TrelloListDto> testTrelloDtoList = new ArrayList<>();
        testTrelloDtoList.add(trelloList1);

        //When
        List<TrelloList> trelloList = trelloMapper.mapToList(testTrelloDtoList);

        //Then
        Assert.assertEquals(1, testTrelloDtoList.size());
        Assert.assertEquals("Lista testowa zakupow",testTrelloDtoList.get(0).getName());
    }

    @Test
    public void testMapToBoardDtoMapper(){
        //Given

        TrelloBoard trelloBoard1 = new TrelloBoard("1", "Zadania z Kodilli modul 21", new ArrayList<>());
        TrelloBoard trelloBoard2 = new TrelloBoard("2", "Zadania WEB Dev", new ArrayList<>());
        List<TrelloBoard> trelloBoardList =  new ArrayList<>();
        trelloBoardList.add(trelloBoard1);
        trelloBoardList.add(trelloBoard2);

        //When
        List<TrelloBoardDto> trelloBoardDtos =  trelloMapper.mapToBoardsDto(trelloBoardList);

        //Then
        Assert.assertEquals(2, trelloBoardDtos.size());
        Assert.assertEquals("Zadania WEB Dev",trelloBoardDtos.get(1).getName());
    }

    @Test
    public void testMapToBoardMapper(){
        //Given
        TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto("1", "Zadania z Kodilli modul 21", new ArrayList<>());
        TrelloBoardDto trelloBoardDto2 = new TrelloBoardDto("2", "Zadania WEB Dev", new ArrayList<>());
        List<TrelloBoardDto> trelloBoardDtoList =  new ArrayList<>();
        trelloBoardDtoList.add(trelloBoardDto1);
        trelloBoardDtoList.add(trelloBoardDto2);

        //When
        List<TrelloBoard> trelloBoards =  trelloMapper.mapToBoards(trelloBoardDtoList);

        //Then
        Assert.assertEquals(2, trelloBoards.size());
        Assert.assertEquals("Zadania WEB Dev",trelloBoards.get(1).getName());
    }

    @Test
    public void testMapToCardMapper(){
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto ("Web dev", "Napisz kod do Styles.css", "1", "2");
        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        Assert.assertEquals("Web dev", trelloCard.getName());
    }

    @Test
    public void testMapToCardDtoMapper(){
        //Given
        TrelloCard trelloCard = new TrelloCard ("Android", "Napisz kod do w Android studio", "1", "1");
        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //Then
        Assert.assertEquals("Android", trelloCardDto.getName());
    }
}
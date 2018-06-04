package com.crud.tasks.service;


import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrelloService {
    @Autowired
    private TrelloClient trelloClient;

    @Autowired
    private SimpleEmailService emailService;



}

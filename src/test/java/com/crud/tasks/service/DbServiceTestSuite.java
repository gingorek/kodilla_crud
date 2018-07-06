package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.facade.TrelloFacade;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTestSuite {

    @InjectMocks
    DbService dbService;

    @Mock
    TaskRepository taskRepository;

    @Test
    public void testGettingAllTasks(){
        //Given
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1l, "first to test", "this is just a test task"));
        taskList.add(new Task(2l, "second to test", "this is another test task"));
        taskList.add(new Task(3l, "third to test", "and one more"));

        when(dbService.getAllTasks()).thenReturn(taskList);

        //When
        List<Task> fetchedTaskList = dbService.getAllTasks();

        //Then
        assertNotNull(fetchedTaskList);
        assertEquals(3,fetchedTaskList.size());
    }

    @Test
    public void testGettingOneTask(){
        //Given
        Task task1 = new Task(1l, "first to test", "this is just a test task");

        when(dbService.getTask(1l)).thenReturn(java.util.Optional.ofNullable(task1));

        //When
        Optional<Task> fetchedOneTask = dbService.getTask(1l);

        //Then
        assertEquals(fetchedOneTask.get().getTitle() ,"first to test");
        assertNotEquals(null,fetchedOneTask);
    }

    @Test
    public void testSavingTask(){
        //Given
        Task testTask = new Task(1l, "Test Task", "Just for testing");
        when(dbService.saveTask(testTask)).thenReturn(testTask);

        //When
        Task savedTaskToDb = dbService.saveTask(testTask);

        //Then
        assertEquals(savedTaskToDb.getContent(), "Just for testing");
    }
}
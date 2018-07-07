package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.facade.TrelloFacade;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService dbService;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    public void shouldFetchTasks() throws Exception {
        //Given
        List<Task> taskList = new ArrayList<>();
        Task task = new Task(1L, "test task", "this is the test task");
        Task task2= new Task(2L, "test task2", "this is the test task2");
        taskList.add(task);
        taskList.add(task2);

        List<TaskDto> taskDtoList = new ArrayList<>();
        TaskDto taskDto = new TaskDto(1L, "test taskdto", "this is the test taskdto");
        TaskDto taskDto2 = new TaskDto(2L, "test taskdto2", "this is the test taskdto2");
        taskDtoList.add(taskDto);
        taskDtoList.add(taskDto2);

        when(taskMapper.mapToTaskDtoList(taskList)).thenReturn(taskDtoList);
        when(dbService.getAllTasks()).thenReturn(taskList);

//        List<TaskDto> taskDtoList = new ArrayList<>();
//        taskDtoList.add(new TaskDto(1l,"Task test", "this is my test task"));
//        taskDtoList.add(new TaskDto(2l,"Task test2", "this is my test task 2"));
//
//        when(taskMapper.mapToTaskDtoList(dbService.getAllTasks())).thenReturn(taskDtoList);

//        When&Then
//        mockMvc.perform(get("v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$"),hasSize(1)));


//      When&Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].title", is("test taskdto2")))
                .andExpect(jsonPath("$[0].content", is("this is the test taskdto")));
    }

    @Test
    public void shouldFetchTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1l,"test taskDto", "test taskDto content");
        Task task = new Task( 1l, "test task", "test task content");

        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        when(dbService.getTask(task.getId())).thenReturn(Optional.ofNullable(task));

        //When&Then
        mockMvc.perform(get("/v1/task/getTask").param("taskId","1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.title",is("test taskDto")));
    }

    @Test
    public void shouldUpdateTask() throws Exception{
        //Given
        TaskDto taskDto = new TaskDto(1l,"test taskDto", "test taskDto content");
        Task task = new Task( 1l, "test task", "test task content");

        when(taskMapper.mapToTask(any(TaskDto.class))).thenReturn(task);
        when(dbService.saveTask(any(Task.class))).thenReturn(task);
        when(taskMapper.mapToTaskDto(any(Task.class))).thenReturn(taskDto);

        //Conversion from Json to Gson
        Gson gson = new Gson();
        String jsonContent = gson.toJson(task);

        //When&Then

        mockMvc.perform(put("/v1/task/updateTask").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$[0].id",is(1)))
//                .andExpect(jsonPath("$[0].title",is("test task")));

    }

    @Test
    public void shouldDeleteTask() throws Exception {
        //Given

        //Tu uzywamy najpierw metody get poniewaz musimy miec jakis Task do skasowania ?
        Task task = new Task( 1l, "test task", "test task content");
        when(dbService.getTask(task.getId())).thenReturn(Optional.ofNullable(task));

        //When&Then
        mockMvc.perform(delete("/v1/task/deleteTask").param("taskId","1").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldSaveTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1l,"test taskDto", "test taskDto content");
        Task task = new Task(1l, "test task", "test task content");

        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        when(dbService.saveTask(any(Task.class))).thenReturn(task);

//        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
//        when(taskMapper.mapToTask(any(TaskDto.class))).thenReturn(task);
//        when(dbService.saveTask(task)).thenReturn(task);

        //Conversion from Json to Gson
        Gson gson = new Gson();
        String jsonContent = gson.toJson(task);

        //When&Then
        mockMvc.perform(post("/v1/task/createTask").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$.id",is("1")))
//                .andExpect(jsonPath("$.title",is("test task")))
//                .andExpect(jsonPath("$.content",is("test task content")));
    }
}
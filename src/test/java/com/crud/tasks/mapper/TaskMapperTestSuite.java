package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TaskMapperTestSuite {

    @InjectMocks
    private TaskMapper taskMapper;

    @Test
    public void testMapToTaskMapper(){
        //Given
        TaskDto taskDtoTest = new TaskDto(1l,"Module 21.2", "Start new submodule in the weekend.");
        //When
        Task taskTest = taskMapper.mapToTask(taskDtoTest);
        //Then
        Assert.assertEquals("Module 21.2", taskTest.getTitle());
    }

    @Test
    public void testMapToTaskDtoMapper(){
        //Given
        Task taskTest1 = new Task(1l,"Work ToDo", "Check my mail");
        //When
        TaskDto taskDtoTest = taskMapper.mapToTaskDto(taskTest1);
        //Then
        Assert.assertEquals("Work ToDo", taskDtoTest.getTitle());
    }

    @Test
    public void testMapToTaskDtoListMapper(){
        //Given
        Task taskTest1 = new Task(1l,"Work ToDo", "Send email to Daniel");
        Task taskTest2 = new Task(2l,"Vacation ToDo", "Check hotel prices.");
        Task taskTest3 = new Task(3l,"Home ToDo", "Make a laundry");

        List<Task> taskList = new ArrayList<>();
        taskList.add(taskTest1);
        taskList.add(taskTest2);
        taskList.add(taskTest3);

        //When
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(taskList);

        //Then
        Assert.assertEquals(3, taskDtoList.size());
        Assert.assertEquals("Vacation ToDo", taskDtoList.get(1).getTitle());
    }
}
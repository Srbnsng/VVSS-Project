package tasks.tests.services;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tasks.model.ArrayTaskList;
import tasks.model.Task;
import tasks.service.TasksService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

class TasksServiceTest {

    @Mock
    private ArrayTaskList arrayTaskList;

    @InjectMocks
    private TasksService tasksService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testGetObservableList() throws Exception {
        //set up
        Date date = new Date(2013, Calendar.FEBRUARY, 7);
        Task task = new Task("Task",date);
        Date date2 = new Date(2016, Calendar.FEBRUARY, 7);
        Task task2 = new Task("Task2",date2);

        //act
        this.arrayTaskList.add(task);
        this.arrayTaskList.add(task2);
        Mockito.doReturn(Arrays.asList(task)).when(arrayTaskList).getAll();
        ObservableList<Task> observableList = this.tasksService.getObservableList();

        //assert and verify
        Mockito.verify(arrayTaskList, times(1)).getAll();
        assertEquals(observableList.get(0),task);
        assertEquals(observableList.size(),1);
    }


    @Test
    void testFilterList() throws Exception {
        //set up
        Date date =  new Date(2020, Calendar.MARCH, 11,12,0);
        Task task = new Task("Task", new Date(2020,  Calendar.MARCH, 12,3,0), new Date(2020,  Calendar.MARCH, 13,3,0), 3);
        Date anotherDate = new Date(2005, Calendar.FEBRUARY, 7);
        Date testDate =  new Date(2020,  Calendar.APRIL, 16,12,0);
        Task anotherTask = new Task("Task", anotherDate);

        //act
        this.arrayTaskList.add(task);
        this.arrayTaskList.add(anotherTask);
        Mockito.doReturn(Arrays.asList(task)).when(arrayTaskList).getAll();
        ArrayList<Task> taskList = (ArrayList<Task>) this.tasksService.filterTasks(date, testDate);

        //assert
        assertEquals(taskList.get(0), task);
    }


}
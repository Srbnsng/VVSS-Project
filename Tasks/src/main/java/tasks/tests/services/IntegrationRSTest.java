package tasks.tests.services;

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

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;

public class IntegrationRSTest {

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
    void testDeleteTask() throws Exception {

        //set up
        Date date = new Date(2013, Calendar.FEBRUARY, 7);
        Task task = new Task("Task",date);

        //act
        Mockito.doNothing().when(arrayTaskList).add(task);
        this.arrayTaskList.add(task);
        Mockito.verify(arrayTaskList, times(1)).add(task);
        Mockito.when(arrayTaskList.remove(task)).thenReturn(true);
        boolean ok = this.tasksService.deleteTask(task);
        Mockito.verify(arrayTaskList, times(1)).remove(task);

        //assert
        assertTrue (ok);
    }

    @Test
    void testAddTask(){
        //set up
        Date date = new Date(2013, Calendar.FEBRUARY, 7);
        Task task = new Task("Task",date);

        //act
        Mockito.when(arrayTaskList.getAll()).thenReturn(Arrays.asList(task));

        //assert and verify
        assertEquals(this.tasksService.getObservableList().size(),1);
        Mockito.verify(arrayTaskList, times(1)).getAll();
    }


}

package tasks.services;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;

public class IntegrationERSTest {

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
    void testGetAll(){
        //set up
        Date date = new Date(2013, Calendar.FEBRUARY, 7);
        Task task = new Task("Task",date);

        //act
        Mockito.when(arrayTaskList.getAll()).thenReturn(Collections.singletonList(task));


        //assert and verify
        assertEquals(this.tasksService.getObservableList().get(0).getTitle(),"Task");
        assertEquals(this.tasksService.getObservableList().get(0).getTime(),date);

        Mockito.verify(arrayTaskList, times(2)).getAll();

    }

    @Test
    void testeAdd() throws Exception {
        //set up
        Date date = new GregorianCalendar(2013, Calendar.FEBRUARY, 7).getTime();
        Task task = new Task("Task",date);

        //act
        Mockito.doNothing().when(arrayTaskList).add(task);
        this.arrayTaskList.add(task);

        Mockito.verify(arrayTaskList, times(1)).add(task);

        Mockito.when(arrayTaskList.getAll()).thenReturn(Collections.singletonList(task));
        boolean status = arrayTaskList.getAll().size() == 1;

        //assert and verify
        Mockito.verify(arrayTaskList, times(1)).getAll();


        assertTrue(status);
    }
}

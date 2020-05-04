package tasks;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import tasks.model.Task;
import tasks.model.TasksOperations;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TasksOperationsTest {


    @BeforeAll
    static void init() {
    }

    //F02_TC01
    @Test
    void filterTestWhereTaskListIsEmpty() {
        //set up
        TasksOperations tasksOperations2;
        List<Task> taskList = new ArrayList<>();
        ObservableList<Task> tasksList = FXCollections.observableArrayList(taskList);
        tasksOperations2 = new TasksOperations(tasksList);
        //act
        List<Task> returnedFilteredList=(List<Task>)tasksOperations2.incoming( new Date(2020, Calendar.FEBRUARY, 12,12,0), new Date(2020, Calendar.JUNE, 12,12,0));

        //assert
        assertEquals(0, returnedFilteredList.size());
    }

    //F02_TC02
    @Test
    void filterTestWhereTaskListIsValid() {
        //set up
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task("task1", new Date(2020,  Calendar.MARCH, 12,3,0), new Date(2020,  Calendar.MARCH, 13,3,0), 3));
        taskList.add(new Task("task2", new Date(2020,  Calendar.MARCH, 12,3,0), new Date(2020,  Calendar.MARCH, 13,3,0), 3));
        taskList.add(new Task("task3", new Date(2020,  Calendar.MARCH, 12,2,0), new Date(2020,  Calendar.MARCH, 13,3,0), 3));
        ObservableList<Task> tasksList = FXCollections.observableArrayList(taskList);
        TasksOperations tasksOperations = new TasksOperations(tasksList);

        //act
        List<Task> returnedFilteredList=(List<Task>)tasksOperations.incoming( new Date(2020, Calendar.MARCH, 11,12,0), new Date(2020,  Calendar.APRIL, 16,12,0));

        //assert
        assertEquals(3, returnedFilteredList.size());
    }


    //F02_TC03
    @Test
    void filterTestWhereNextTimeIsNull() {
        //set up
        List<Task> taskList = new ArrayList<>();
        Task t=new Task("task1", new Date(2020,  Calendar.MARCH, 12,3,0), new Date(2020,  Calendar.MARCH, 13,3,0), 3);
        t.active=false;
        taskList.add(t);
        ObservableList<Task> tasksList = FXCollections.observableArrayList(taskList);
        TasksOperations tasksOperations = new TasksOperations(tasksList);

        //act
        List<Task> returnedFilteredList=(List<Task>)tasksOperations.incoming( new Date(2020, Calendar.MARCH, 11,12,0), new Date(2020,  Calendar.APRIL, 16,12,0));

        //assert
        assertEquals(0, returnedFilteredList.size());
    }

    //F02_TC04
    @Test
    void filterTestWhereNextTimeIsBeforeEnd() {
        //set up
        List<Task> taskList = new ArrayList<>();
        Task t=new Task("task1", new Date(2020,  Calendar.MARCH, 12,3,0), new Date(2020,  Calendar.MARCH, 13,3,0), 3);
        taskList.add(t);
        ObservableList<Task> tasksList = FXCollections.observableArrayList(taskList);
        TasksOperations tasksOperations = new TasksOperations(tasksList);

        //act
        List<Task> returnedFilteredList=(List<Task>)tasksOperations.incoming( new Date(2020, Calendar.MARCH, 11,12,0), new Date(2020,  Calendar.APRIL, 16,12,0));

        //assert
        assertEquals(1, returnedFilteredList.size());
    }


    //F02_TC05
    @Test
    void filterTestWhereNextTimeEqualsEnd() {
        //set up
        List<Task> taskList = new ArrayList<>();
        Task t=new Task("task1", new Date(2020,  Calendar.MARCH, 12,3,0), new Date(2020,  Calendar.MARCH, 13,3,0), 3);
        taskList.add(t);
        ObservableList<Task> tasksList = FXCollections.observableArrayList(taskList);
        TasksOperations tasksOperations = new TasksOperations(tasksList);

        //act
        List<Task> returnedFilteredList=(List<Task>)tasksOperations.incoming( new Date(2020, Calendar.MARCH, 11,12,0), new Date(2020,  Calendar.MARCH, 12,3,0));

        //assert
        assertEquals(1, returnedFilteredList.size());
    }

    //F02_TC06
    @Test
    void invalidTestWhereDateIsInvalid(){
        //set up
        List<Task> taskList = new ArrayList<>();
        //reversed dates end<start
        Task t=new Task("task1", new Date(2020,  Calendar.MARCH, 13,3,0),new Date(2019,  Calendar.MARCH, 10,3,0),  3);
        taskList.add(t);
        ObservableList<Task> tasksList = FXCollections.observableArrayList(taskList);
        TasksOperations tasksOperations = new TasksOperations(tasksList);

        //act
        List<Task> returnedFilteredList=(List<Task>)tasksOperations.incoming( new Date(2020, Calendar.MARCH, 11,12,0), new Date(2020,  Calendar.MARCH, 13,3,0));


        //assert
        assertEquals(0, returnedFilteredList.size());
    }


}
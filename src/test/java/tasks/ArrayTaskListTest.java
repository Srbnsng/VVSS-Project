package tasks;

import org.junit.jupiter.api.*;
import tasks.model.ArrayTaskList;
import tasks.model.LinkedTaskList;
import tasks.model.Task;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

//The @Tag annotation is useful when we want to create a test pack with selected tests.
@Tag("F01_BBT")
class ArrayTaskListTest {
    private static Task task;
    private static ArrayTaskList arrayTaskList;

    @BeforeAll
    static void init() {
        task = new Task("Description", new Date(2020, Calendar.APRIL, 30), new Date(2020, Calendar.APRIL, 30), 2);
    }

    @BeforeEach@DisplayName("Test 1")
    void setUp() {
        arrayTaskList=new ArrayTaskList();
        task.setTitle("Description");
        task.setStart(new Date(2020, Calendar.APRIL, 30));
        task.setEnd(new Date(2020, Calendar.APRIL, 30));
        task.setInterval(2);
    }

    @Test
    void TC01_EC() throws Exception {
        arrayTaskList.add(task);
        assertEquals(1, arrayTaskList.getAll().size());
    }

    @Test
    void TC02_EC() throws Exception {
        task.setInterval(6);
        arrayTaskList.add(task);
        assertEquals(1, arrayTaskList.getAll().size());
    }

    @Test
    void TC03_EC() {
        task.setTitle("");
        Exception exception = assertThrows(Exception.class, () -> {
            arrayTaskList.add(task);
        });

        String expectedMessage = "Empty description";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void TC04_EC() {
        task.setInterval(-4);
        Exception exception = assertThrows(Exception.class, () -> {
            arrayTaskList.add(task);
        });

        String expectedMessage = "Invalid interval";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }


    @Test
    void TC01_BVA() throws Exception {
        task.setTitle("M");
        arrayTaskList.add(task);
        assertEquals(1, arrayTaskList.getAll().size());
    }

    @Test
    void TC02_BVA() {
        task.setTitle("");
        Exception exception = assertThrows(Exception.class, () -> {
            arrayTaskList.add(task);
        });

        String expectedMessage = "Empty description";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void TC03_BVA() throws Exception {
        task.setInterval(0);
        arrayTaskList.add(task);
        assertEquals(1, arrayTaskList.getAll().size());
    }

    @Test
    void TC04_BVA() throws Exception {
        task.setInterval(-1);
        Exception exception = assertThrows(Exception.class, () -> {
            arrayTaskList.add(task);
        });

        String expectedMessage = "Invalid interval";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }



    @AfterAll
    static void after() {
        System.out.println("Finished tests");
    }

}
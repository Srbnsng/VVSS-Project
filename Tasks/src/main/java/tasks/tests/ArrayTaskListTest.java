package tasks.tests;

import org.junit.jupiter.api.*;
import tasks.model.ArrayTaskList;
import tasks.model.LinkedTaskList;
import tasks.model.Task;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

//The @Tag annotation is useful when we want to create a test pack with selected tests.
@Tag("F01_BBT")
class ArrayTaskListTest {
    private static Task task;
    private static ArrayTaskList arrayTaskList;

    @BeforeAll
    static void init() {
        arrayTaskList=new ArrayTaskList();
        task = new Task("Description", new Date(2020, 3, 30), new Date(2020, 3, 30), 2);
    }

    @BeforeEach
    void setUp() {
        task.setTitle("Description");
        task.setStart(new Date(2020, 3, 30));
        task.setEnd(new Date(2020, 3, 30));
        task.setInterval(2);
    }

    @RepeatedTest(value = 5, name = "{displayName} {currentRepetition}/{totalRepetitions}")
    @DisplayName("RepeatingTest")
    void TC01_EC(RepetitionInfo repInfo) {
        arrayTaskList.add(task);
        assertTrue(arrayTaskList.getAll().size() == repInfo.getCurrentRepetition());
    }

    @Test
    void TC02_EC() {
        task.setInterval(6);
        arrayTaskList.add(task);
        assertTrue(arrayTaskList.getAll().size() == 1);
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
    void TC01_BVA() {
        task.setTitle("M");
        arrayTaskList.add(task);
        assertTrue(arrayTaskList.getAll().size() == 1);
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
    void TC03_BVA() {
        task.setInterval(0);
        arrayTaskList.add(task);
        assertTrue(arrayTaskList.getAll().size() == 1);
    }

    @Test
    void TC04_BVA() {
        task.setInterval(-1);
        arrayTaskList.add(task);
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
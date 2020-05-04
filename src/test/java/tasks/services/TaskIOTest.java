package tasks.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.utils.TaskIO;


class TaskIOTest {
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testInvalidInterval(){
        try{
            TaskIO.getFormattedInterval(0);
        }
        catch (Exception ex){
            assert (ex.getMessage().equals("Interval <= 0"));
        }
    }

    @Test
    void testValidInterval(){
        try{
            String interval = TaskIO.getFormattedInterval(10);
            System.out.println(interval);
            assert ( interval.contains("10"));
        }
        catch (Exception ex){
            assert  false;
        }
    }

    @Test
    void testHugeInterval(){
        try{
            String interval = TaskIO.getFormattedInterval(10000);
            System.out.println(interval);
            assert (interval.contains("2") && interval.contains("46") && interval.contains("40") );
        }
        catch (Exception ex){
            assert  false;
        }
    }


    @Test
    void testSmallInterval(){
        try{
            String interval = TaskIO.getFormattedInterval(1);
            System.out.println(interval);
            assert (interval.contains("1") && interval.contains("second"));
        }
        catch (Exception ex){
            assert  false;
        }
    }

    @Test
    void testMediumInterval(){
        try{
            String interval = TaskIO.getFormattedInterval(1000);
            System.out.println(interval);
            assert(interval.contains("16") && interval.contains("40"));
        }
        catch (Exception ex){
            assert  false;
        }
    }

    @Test
    void testMaxHugeInterval(){
        try{
            String interval = TaskIO.getFormattedInterval(1000000);
            System.out.println(interval);
            assert ( interval.contains("11 days"));
        }
        catch (Exception ex){
            assert  false;
        }
    }

    @Test
    void testHoursAndMinutes(){
        try{
            String interval = TaskIO.getFormattedInterval(9960);
            System.out.println(interval);
            assert(interval.contains("2 hours") && interval.contains("46 minutes"));
            assert(!interval.contains("seconds"));
        }
        catch (Exception ex){
            assert  false;
        }
    }

    @Test
    void testOnlyHours(){
        try{
            String interval = TaskIO.getFormattedInterval(7200);
            assert(interval.contains("2 hours"));
            assert(!interval.contains("minutes"));
            assert(!interval.contains("seconds"));
        }
        catch (Exception ex){
            assert  false;
        }
    }
}
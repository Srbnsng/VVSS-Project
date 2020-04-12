package tasks.model;

import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Date;

public class TasksOperations {

    public ArrayList<Task> tasks;

    public TasksOperations(ObservableList<Task> tasksList) {
        tasks = new ArrayList<>();
        tasks.addAll(tasksList);
    }


    public Iterable<Task> incoming(Date start, Date end) {
        ArrayList<Task> incomingTasks = new ArrayList<>();
        int i = 0;
        while (i < tasks.size()) {
            Task t = tasks.get(i);
            Date nextTime = t.nextTimeAfter(start);
            if (nextTime != null) {
                if (nextTime.before(end)) {
                    incomingTasks.add(t);
                } else if (nextTime.equals(end)) {
                    incomingTasks.add(t);
                }
            }
            i++;
        }
        return incomingTasks;//12
    }
}

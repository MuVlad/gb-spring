package muslimov.vlad.gbspring.model;

import org.springframework.context.ApplicationListener;

public class TaskUpdatedListener implements ApplicationListener<TaskUpdatedEvent> {
    @Override
    public void onApplicationEvent(TaskUpdatedEvent event) {
        System.out.println("Task " + event.getTask().getName() + " updated");
    }
}

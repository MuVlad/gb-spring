package muslimov.vlad.gbspring.model;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class TaskUpdatedEvent extends ApplicationEvent {

    private ITask task;

    public TaskUpdatedEvent(Object source, ITask task) {
        super(source);
        this.task = task;
    }
}
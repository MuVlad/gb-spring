package muslimov.vlad.gbspring.model.factory;

import muslimov.vlad.gbspring.model.ITask;
import muslimov.vlad.gbspring.model.Task;
import muslimov.vlad.gbspring.model.TaskStatus;

public class TaskDoneFactory implements TaskFactory{
    @Override
    public ITask createTask(String name) {

        return Task.builder()
            .name(name)
            .status(TaskStatus.DONE)
            .build();
    }
}

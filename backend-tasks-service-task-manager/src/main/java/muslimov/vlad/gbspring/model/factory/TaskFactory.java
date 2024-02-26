package muslimov.vlad.gbspring.model.factory;

import muslimov.vlad.gbspring.model.ITask;

public interface TaskFactory {
    ITask createTask(String name);
}

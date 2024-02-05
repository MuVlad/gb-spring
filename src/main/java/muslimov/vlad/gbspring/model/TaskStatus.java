package muslimov.vlad.gbspring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TaskStatus {
    TODO("К исполнению"),
    IN_PROGRESS("В процессе"),
    DONE("Выпалнена");

    private final String title;
}
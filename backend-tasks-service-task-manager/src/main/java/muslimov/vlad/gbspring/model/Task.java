package muslimov.vlad.gbspring.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = Task.TASK_TABLE_NAME)
public class Task implements ITask {

    public static final String TASK_TABLE_NAME = "task";
    private static final String SEQ_NAME = "task_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(
        name = SEQ_NAME, sequenceName = SEQ_NAME,
        initialValue = 100, allocationSize = 1
    )
    private Long id;
    private String name;
    private String description;
    @CreationTimestamp
    private LocalDateTime createAt;
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    @ManyToOne
    private User user;

    // Паттерн Builder
    public static class TaskBuilder {
        private Long id;
        private String name;
        private String description;
        private LocalDateTime createAt;
        private TaskStatus status;
        private User user;

        public TaskBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        public TaskBuilder name(final String name) {
            this.name = name;
            return this;
        }

        public TaskBuilder description(final String description) {
            this.description = description;
            return this;
        }

        public TaskBuilder createAt(final LocalDateTime createAt) {
            this.createAt = createAt;
            return this;
        }

        public TaskBuilder status(final TaskStatus status) {
            this.status = status;
            return this;
        }

        public TaskBuilder user(final User user) {
            this.user = user;
            return this;
        }

        public Task build() {
            return new Task(this.id, this.name, this.description, this.createAt, this.status, this.user);
        }
    }
}

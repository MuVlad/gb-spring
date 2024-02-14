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
public class Task {

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
}

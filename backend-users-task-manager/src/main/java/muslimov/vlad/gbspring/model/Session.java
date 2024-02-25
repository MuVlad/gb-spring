package muslimov.vlad.gbspring.model;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = Session.SESSION_TABLE_NAME)
public class Session {
    public static final String SESSION_TABLE_NAME = "sessions";
    private static final String SEQ_NAME = "session_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(
        name = SEQ_NAME, sequenceName = SEQ_NAME,
        initialValue = 3,
        allocationSize = 1
    )
    private Long id;
    @ManyToOne
    private User user;
}

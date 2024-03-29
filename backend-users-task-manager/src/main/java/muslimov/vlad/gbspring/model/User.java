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
@Table(name = User.USER_TABLE_NAME)
public class User {

    public static final String USER_TABLE_NAME = "users";
    private static final String SEQ_NAME = "user_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(
        name = SEQ_NAME, sequenceName = SEQ_NAME,
        initialValue = 3,
        allocationSize = 1
    )
    private Long id;
    private String name;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
}

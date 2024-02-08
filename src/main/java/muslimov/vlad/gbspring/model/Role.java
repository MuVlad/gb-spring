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
@Table(name = Role.ROLE_TABLE_NAME)
public class Role {

    public static final String ROLE_TABLE_NAME = "roles";
    private static final String SEQ_NAME = "role_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(
        name = SEQ_NAME, sequenceName = SEQ_NAME,
        initialValue = 100, allocationSize = 1
    )
    private Long id;
    private String name;
}

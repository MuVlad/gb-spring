package muslimov.vlad.gbspring.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

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
        allocationSize = 1
    )
    private Long id;
    private String name;
    private String password;
    @ManyToMany
    @JoinTable(
        name = "users_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Collection<Role> roles;
}

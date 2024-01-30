package muslimov.vlad.gbspring.model;


import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record Task(
        Long id,
        String name,
        LocalDateTime createAt,
        String description
) {
}

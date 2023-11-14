package se360.shortlinker.restserver.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "links")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Link {
    @Id @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Getter @Setter
    @Column(name = "url", nullable = false)
    private String url;

    @Getter @Setter
    @Column(name = "short_url", nullable = false, unique = true)
    private String shortUrl;

    @Getter @Setter
    @Column(name = "clicks", nullable = false)
    private Long clicks;

    @Getter @Setter
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @Getter @Setter
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Getter @Setter
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @Getter @Setter
    @Column(name = "deleted_at", nullable = true)
    private Instant deletedAt;

    @Getter @Setter
    @Column(name = "deleted", nullable = false)
    private Boolean deleted;


}

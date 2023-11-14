package se360.shortlinker.restserver.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Getter
    private Long id;

    @Getter @Setter
    @Column(name = "username", nullable = false, unique = true)
    @Size(min = 4, max = 64)
    private String username;

    @Getter @Setter
    @Column(name = "password", nullable = false)
    @Size(min = 4, max = 64)
    private String password;

    @Getter @Setter
    @Column(name = "email", nullable = false, unique = true)
    @Email
    private String email;

    @Getter @Setter
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    @ToString.Exclude
    private List<Link> links;

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

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        User user = (User) o;
        return getId() != null && Objects.equals(getId(), user.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}

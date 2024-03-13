package vk.app.user;

import vk.app.audit.AuditListener;
import vk.app.audit.Status;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditListener.class)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String user;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    private String url;

    private String intention;

    @Enumerated(EnumType.STRING)
    private Status status;

    public AuditEntity(Status status, String userEmail, LocalDateTime now, String url, String intention) {
        this.status = status;
        this.user = userEmail;
        this.lastModifiedDate = now;
        this.url = url;
        this.intention = intention;
    }
}
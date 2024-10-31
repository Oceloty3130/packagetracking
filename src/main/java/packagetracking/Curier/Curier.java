package packagetracking.Curier;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Data
public class Curier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String email;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private Date employmentDay;

    private Date resignationDay = null;

}

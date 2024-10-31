package packagetracking.Courier;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Data
public class Courier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String email;

    private Status status;

    private Integer managerId;

    /*@CreationTimestamp
    @Column(updatable = false, nullable = false)
    private Date employmentDay;

    private Date resignationDay = null;*/
}

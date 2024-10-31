package packagetracking.pkg;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import packagetracking.Curier.Curier;

import java.util.Date;

@Entity
@Data
public class Package {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private Date createdOn;

    private String deliveryAddress;

    private Status status;

    @ManyToOne
    private Curier curier;
}
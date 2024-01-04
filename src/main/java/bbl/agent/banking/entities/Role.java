package bbl.agent.banking.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ROLE")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String UserRoleName;
}

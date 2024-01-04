package bbl.agent.banking.entities;

import jakarta.persistence.*;
import lombok.Data;
;
/**
 * @author Nusrat Jahan Tarin
 */
@Data
@Entity
@Table(name = "USERS", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"})
})
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nameEn;
    private String nameBn;
    private String username;
    private String password;
}



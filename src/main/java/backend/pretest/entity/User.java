package backend.pretest.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(nullable = false, updatable = false, length = 36, unique = true)
    private String userId;

    @Column(length = 100)
    private String email;

    @Column(length = 40, unique = true)
    private String userName;

    @Column(length = 20)
    private String nickName;

    @Column(length = 120)
    private String password;

    @JsonIgnore
    @Column(nullable = false)
    private Date last_password = new Date();

}


package co.istad.userservice.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("users")
public class User {
    @Id
    private String id;
    private String username;
    private String email;
    private String address;
    private String phone;
}

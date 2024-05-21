package org.gb.movieapp.Model.Request;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PasswordRequest {
    String password;
    String newPassword;
    String confirmPassword;

}

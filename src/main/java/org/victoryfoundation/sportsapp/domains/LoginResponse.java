package org.victoryfoundation.sportsapp.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.victoryfoundation.sportsapp.entity.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {

    private User user;
    private String accessToken;

}

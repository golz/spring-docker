package sh.now.goldwin.springdocker.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import sh.now.goldwin.springdocker.model.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserRequest {
  private String id;
  private String username;

  public User toUser() {
    return User.builder()
        .id(getId())
        .username(getUsername())
        .build();
  }
}

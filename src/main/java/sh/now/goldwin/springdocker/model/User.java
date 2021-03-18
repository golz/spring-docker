package sh.now.goldwin.springdocker.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import sh.now.goldwin.springdocker.model.response.UserResponse;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Document("users")
public class User {
  public static final String COLLECTION = "users";

  @Id
  private String id;
  private String username;

  public UserResponse toUserResponse() {
    return UserResponse.builder()
        .id(getId())
        .username(getUsername())
        .build();
  }
}

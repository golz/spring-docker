package sh.now.goldwin.springdocker.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import sh.now.goldwin.springdocker.model.User;
import sh.now.goldwin.springdocker.model.request.UserRequest;
import sh.now.goldwin.springdocker.model.response.UserResponse;
import sh.now.goldwin.springdocker.repository.UserRepository;

@Component
@AllArgsConstructor
public class UserController {

  private final UserRepository userRepository;

  public Mono<ServerResponse> findAll(ServerRequest request) {
    return ServerResponse.ok()
        .body(userRepository.findAll().map(User::toUserResponse), UserResponse.class);
  }

  public Mono<ServerResponse> findById(ServerRequest request) {
    String id = request.pathVariable("id");
    return ServerResponse.ok()
        .body(userRepository.findById(id), UserResponse.class);
  }

  public Mono<ServerResponse> findByUsername(ServerRequest request) {
    String username = request.pathVariable("username");
    return ServerResponse.ok()
        .body(userRepository.findByUsername(username), User.class);
  }

  public Mono<ServerResponse> save(ServerRequest request) {
    Mono<UserRequest> user = request.bodyToMono(UserRequest.class);
    Mono<User> result = user.map(UserRequest::toUser)
        .flatMap(userRepository::save);
    return ServerResponse.ok().body(result.map(User::toUserResponse), UserResponse.class);
  }

  public Mono<ServerResponse> delete(ServerRequest request) {
    String id = request.pathVariable("id");
    userRepository.deleteById(id).subscribe();
    return ServerResponse.ok().build();
  }
}

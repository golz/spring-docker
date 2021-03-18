package sh.now.goldwin.springdocker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import sh.now.goldwin.springdocker.controller.UserController;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class RouterConfig {

  @Bean
  public RouterFunction<ServerResponse> appContext() {
    return route()
        .GET("/", serverRequest -> ok()
          .body(Mono.just("spring-docker"), String.class))
        .GET("/ping", serverRequest -> ok()
          .body(Mono.just("ping"), String.class))
        .build();
  }

  @Bean
  public RouterFunction<ServerResponse> userControllerRoute(UserController userController) {
    return route()
        .path("users", builder -> builder
            .GET("", userController::findAll)
            .GET("/{id}", userController::findById)
            .GET("/user/{username}", userController::findByUsername)
            .POST("", userController::save)
            .DELETE("/{id}", userController::delete)
            .build())
        .build();
  }
}

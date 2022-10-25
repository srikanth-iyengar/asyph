package io.asyph.gateway.configuration;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.http.server.reactive.ServerHttpRequest;

public class RouterValidator {

    public static final List<Endpoint> openApiEndpoints = List.of(
            );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri.uri));

    class Endpoint {
        String uri;
        List<String> roles;
        

        public Endpoint(String uri, String ...roles) {
            this.uri = uri;
            this.roles = List.of(roles);
        }
    }
}

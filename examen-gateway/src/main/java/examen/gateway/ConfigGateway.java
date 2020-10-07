package examen.gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigGateway {
	
	@Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/persona/**")
                        .uri("http://localhost:8081/persona")
                        .id("personaModulo"))
                .route(r -> r.path("/personaa/**")
                        .uri("http://localhost:8082/persona")
                        .id("persona2Modulo"))
                .route(r -> r.path("/personaaa/**")
                        .uri("http://localhost:8083/persona")
                        .id("persona3Modulo"))

                .build();
    }
	
}

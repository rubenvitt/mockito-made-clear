package dev.rubeen.java.learn.mockito.chapter01;

import java.util.Map;
import java.util.stream.Collectors;

public class AstroService {
    private final Gateway<AstroResponse> gateway;

    public AstroService (final Gateway<AstroResponse> gateway) {
        this.gateway = gateway;
    }

    public Map<String, Long> getAstrodata() {
        return gateway.get().getPeople().stream()
            .collect(
                Collectors.groupingBy(
                    Assignment::getCraft,
                    Collectors.counting()
                )
            );
    }
}

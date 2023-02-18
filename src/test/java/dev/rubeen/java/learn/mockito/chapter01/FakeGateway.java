package dev.rubeen.java.learn.mockito.chapter01;

import dev.rubeen.java.learn.mockito.chapter01.Assignment;
import dev.rubeen.java.learn.mockito.chapter01.AstroResponse;
import dev.rubeen.java.learn.mockito.chapter01.Gateway;

import java.util.List;

public class FakeGateway implements Gateway<AstroResponse> {
    @Override
    public AstroResponse get () {
        return new AstroResponse(
            3,
            "success",
            List.of(
                new Assignment("John", "USS Enterprise"),
                new Assignment("Jane", "ISS"),
                new Assignment("Jack", "Hubble Telescope")
            )
        );
    }
}

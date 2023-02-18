package dev.rubeen.java.learn.mockito.chapter01;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AstroServiceTest {

    @Mock
    private Gateway<AstroResponse> gateway;

    @InjectMocks
    private AstroService service;

    private final AstroResponse mockAstroResponse = new AstroResponse(
            3,
            "success",
            List.of(
                    new Assignment("John", "USS Enterprise"),
                    new Assignment("Jane", "ISS"),
                    new Assignment("Jack", "Hubble Telescope")
            )
    );

    @Test
    void testAstroData_usingInjectedMockGateway () {
        when(gateway.get()).thenReturn(mockAstroResponse);

        final Map<String, Long> astrodata = service.getAstrodata();

        assertThat(astrodata)
                .containsEntry("USS Enterprise", 1L)
                .containsEntry("ISS", 1L)
                .containsEntry("Hubble Telescope", 1L);

        astrodata.forEach((craft, number) -> {
            System.out.println(craft + ": " + number);

            assertThat(number).isPositive();
            assertThat(craft).isNotBlank();
        });
        verify(gateway).get();
    }

    @Test
    void testAstroData_usingFailedGateway() {
        when(gateway.get()).thenThrow(new RuntimeException(new IOException("Test")));

        assertThatThrownBy(() -> service.getAstrodata())
                .isInstanceOf(RuntimeException.class)
                .hasCauseInstanceOf(IOException.class)
                .hasMessageContaining("Test");
    }
}

@Nested
class Chapter01 {
    private AstroService service;

    @Test
    void testAstroData_usingRealGateway_withHttpClient () {
        // given
        service = new AstroService(new AstroGateway());

        // when
        final Map<String, Long> astrodata = service.getAstrodata();

        // then
        astrodata.forEach((craft, number) -> {
            System.out.println(craft + ": " + number);

            assertThat(number).isPositive();
            assertThat(craft).isNotBlank();
        });
    }

    @Test
    void testAstroData_usingOwnMockGateway () {
        // given
        service = new AstroService(new FakeGateway());

        // when
        final Map<String, Long> astrodata = service.getAstrodata();

        // then
        astrodata.forEach((craft, number) -> {
            System.out.println(craft + ": " + number);

            assertThat(number).isPositive();
            assertThat(craft).isNotBlank();
        });
    }
}

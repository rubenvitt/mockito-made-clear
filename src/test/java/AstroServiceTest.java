import org.junit.jupiter.api.Test;


import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AstroServiceTest {
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
    void testAstroData_usingOwnMockGateway() {
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

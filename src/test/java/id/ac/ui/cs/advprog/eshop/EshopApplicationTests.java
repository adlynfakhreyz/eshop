package id.ac.ui.cs.advprog.eshop;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class EshopApplicationTests {

    @Test
    void contextLoads() {
        // This test ensures that the Spring application context loads successfully.
        // It is a default smoke test provided by Spring Boot to detect misconfigurations.
        // If any issues exist (e.g., missing dependencies, incorrect configurations),
        // this test will fail when running the test suite.
    }

    @Test
    void testMainMethod() {
        assertDoesNotThrow(() -> EshopApplication.main(new String[]{}));
    }
}

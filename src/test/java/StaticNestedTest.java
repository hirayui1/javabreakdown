import org.example.oop.StaticNestedClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class StaticNestedTest {
    private StaticNestedClass outer;
    private StaticNestedClass.StaticNestedInner inner;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;


    @BeforeEach
    void setUp() {
        outer = new StaticNestedClass();
        inner = new StaticNestedClass.StaticNestedInner();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    void restore() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    @DisplayName("Testing doSomething()")
    void testDoSomething() {
        outer.runInner();
        assertEquals("Hello. 1\r\nHello. 2\r\n", outContent.toString());
    }
}

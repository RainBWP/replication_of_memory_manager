import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

package com.sistemas;


public class Memoria_Traductor_Test {

    private MemoriaTraductor memoriaTraductor;

    @BeforeEach
    public void setUp() {
        memoriaTraductor = new MemoriaTraductor();
    }

    @Test
    public void testTranslateToMemory() {
        String input = "some input";
        String expectedOutput = "expected memory output";
        String actualOutput = memoriaTraductor.translateToMemory(input);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testTranslateFromMemory() {
        String input = "some memory input";
        String expectedOutput = "expected output";
        String actualOutput = memoriaTraductor.translateFromMemory(input);
        assertEquals(expectedOutput, actualOutput);
    }
}

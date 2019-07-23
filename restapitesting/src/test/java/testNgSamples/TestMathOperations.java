package testNgSamples;

import mathOperations.suma;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TestMathOperations {

    @Test
    public void testSumResult()
    {
        suma operacionSuma = new suma(3, 6);

        int resultadoOperacion = operacionSuma.getResult();

        assertEquals(9, resultadoOperacion);
    }
}

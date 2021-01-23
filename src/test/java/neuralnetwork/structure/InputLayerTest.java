package neuralnetwork.structure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class InputLayerTest {

    InputLayer layer = null;

    @BeforeEach
    void setUp() {
        layer = new InputLayer(4);
    }

    @Test
    void createInputLayer() {
        layer.createLayer();
        double[] values = layer.getOutputValues();

        assertEquals(4, values.length);

        for (double value: values)
            assertEquals(0, value);
    }

    @Test
    void createFilledInputLayer() {
        double[] input = {1, 2, 3, 4};
        layer.createLayer(input);
        double[] output = layer.getOutputValues();

        assertEquals(4, output.length);

        int idx = 0;
        for (double value: output)
            assertEquals(input[idx++], value);
    }

    @Test
    void setInputValues() {
        double[] input = {5, 6, 7, 8};
        layer.createLayer();
        layer.setInputValues(input);
        double[] output = layer.getOutputValues();

        assertEquals(4, output.length);

        int idx = 0;
        for (double value: output)
            assertEquals(input[idx++], value);

    }

    @Test
    void testToString() {
        double[] input = {1, 2, 3, 4};
        layer.createLayer(input);
        System.out.println(layer.toString());
    }
}

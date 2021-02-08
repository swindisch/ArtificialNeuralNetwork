package neuralnetwork.structure;

import neuralnetwork.activationfunctions.ActivationEnum;
import neuralnetwork.activationfunctions.ActivationFunctionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class HiddenLayerTest {

    HiddenLayer layer = null;
    double[] input;
    double[][] weights;
    double[] bias;

    @BeforeEach
    void setUp() {
        layer = new HiddenLayer(1, 8, 4, ActivationEnum.LINEAR);

        input = new double[4];
        weights = new double[8][4];
        bias = new double[8];

        for (int i = 0; i < 4; i++) {
            input[i] = i + 1;
        }

        for (int j = 0; j < 8; j++) {
            bias[j] = (1 + j) * 2;

            for (int i = 0; i < 4; i++) {
                weights[j][i] = (j + 1) * (i + 1);
            }
        }
    }

    @Test
    void createHiddenLayer() {
        layer.createLayer();

        double[] outputBias = layer.getBiasValues();
        double[][] outputWeights = layer.getWeights();


        assertEquals(8, outputBias.length);
        assertEquals(8, outputWeights.length);

      //  for (double value: outputBias)
//            assertEquals(0.27707849007413665, value);

    //    for (double[] outputWeight: outputWeights)
  //          assertArrayEquals(new double[]{0.0, 0.0, 0.0, 0.0}, outputWeight);
    }

    @Test
    void createFilledHiddenLayer() {
        layer.createLayer(weights, bias);

        double[] outputBias = layer.getBiasValues();
        double[][] outputWeights = layer.getWeights();


        assertEquals(8, outputBias.length);
        assertEquals(8, outputWeights.length);

        int idx = 0;
        for (double value: outputBias)
            assertEquals(bias[idx++], value);

        idx = 0;
        for (double[] outputWeight: outputWeights)
            assertArrayEquals(weights[idx++], outputWeight);
    }


    @Test
    void setBiasValues() {
        layer.createLayer();
        layer.setBiasValues(bias);
        double[] outputBias = layer.getBiasValues();

        assertEquals(8, outputBias.length);

        int idx = 0;
        for (double value: outputBias)
            assertEquals(bias[idx++], value);
    }

    @Test
    void setInputWeights() {
        layer.createLayer();
        layer.setWeights(weights);
        double[][] outputWeights = layer.getWeights();

        assertEquals(8, outputWeights.length);

        int idx = 0;
        for (double[] outputWeight: outputWeights) {
            assertEquals(4, (outputWeight.length));
            assertArrayEquals(weights[idx++], outputWeight);
        }
    }

    @Test
    void testToString() {
        layer.createLayer(weights, bias);
        System.out.println(layer.toString());
    }

    @Test
    void testCalculation() {
        layer.createLayer(weights, bias);

        double[] layerOutput = layer.calcOutput(input);

        assertEquals(8, layerOutput.length);
        assertArrayEquals(new double[]{32.0, 64, 96.0, 128.0, 160.0, 192.0, 224.0, 256.0}, layerOutput);
    }


    @Test
    void TestBigLayer() throws InterruptedException {
        HiddenLayer bigLayer = new HiddenLayer(1, 256, 784, ActivationEnum.SIGMOID);

        long startTime = System.nanoTime();
        Instant start = Instant.now();

        bigLayer.createLayer();
        Thread.sleep(1000);

        Instant end = Instant.now();
        long stopTime = System.nanoTime();

        System.out.println(Duration.between(start, end));
        System.out.println(stopTime - startTime);

        double[] outputBias = bigLayer.getBiasValues();
        double[][] outputWeights = bigLayer.getWeights();


        assertEquals(256, outputBias.length);
        assertEquals(256, outputWeights.length);

    }
}
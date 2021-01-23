package neuralnetwork;

import neuralnetwork.activationfunctions.ActivationLinear;
import neuralnetwork.neurons.HiddenNeuron;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HiddenNeuronALTest {

    HiddenNeuron singleNeuron = null;

    double bias = 2.0;
    double[] weights = new double[]{1, 2, 3, 4};
    double[] inputsPos = new double[]{4, 3, 2, 1};
    double[] inputsNeg = new double[]{-4, -3, -2, -1};

    @BeforeEach
    void setUp() {
        singleNeuron = new HiddenNeuron(bias, weights, new ActivationLinear());
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void calcSumPos() {
        double sum = singleNeuron.calcSum(inputsPos);
        assertEquals(20.0, sum);
    }

    @Test
    void calcActivationDirectPos() {
        double sum = singleNeuron.calcActivation(inputsPos);
        assertEquals(22.0, sum);
    }

    @Test
    void calcActivationIndirectPos() {
        double sum = singleNeuron.calcSum(inputsPos);
        assertEquals(20.0, sum);

        double activationSum = singleNeuron.calcActivation();
        assertEquals(22.0, activationSum);
    }

    @Test
    void calcSumNeg() {
        double sum = singleNeuron.calcSum(inputsNeg);
        assertEquals(-20.0, sum);
    }

    @Test
    void calcActivationDirectNeg() {
        double sum = singleNeuron.calcActivation(inputsNeg);
        assertEquals(-18.0, sum);
    }

    @Test
    void calcActivationIndirectNeg() {
        double sum = singleNeuron.calcSum(inputsNeg);
        assertEquals(-20.0, sum);

        double activationSum = singleNeuron.calcActivation();
        assertEquals(-18.0, activationSum);
    }
}
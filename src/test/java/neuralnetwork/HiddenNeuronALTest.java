package neuralnetwork;

import neuralnetwork.activationfunctions.ActivationLinear;
import neuralnetwork.activationfunctions.ActivationRLU;
import neuralnetwork.activationfunctions.ActivationSigmoid;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

class HiddenNeuronALTest {

    HiddenNeuron singleNeuron = null;

    double bias = 2.0;
    double[] weights = new double[]{1, 2, 3, 4};
    double[] inputsPos = new double[]{4, 3, 2, 1};
    double[] inputsNeg = new double[]{-4, -3, -2, -1};

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        singleNeuron = new HiddenNeuron(bias, weights, new ActivationLinear());
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void calcSumPos() {
        double sum = singleNeuron.calcSum(inputsPos);
        assertEquals(20.0, sum);
    }

    @org.junit.jupiter.api.Test
    void calcActivationDirectPos() {
        double sum = singleNeuron.calcActivation(inputsPos);
        assertEquals(22.0, sum);
    }

    @org.junit.jupiter.api.Test
    void calcActivationIndirectPos() {
        double sum = singleNeuron.calcSum(inputsPos);
        assertEquals(20.0, sum);

        double activationSum = singleNeuron.calcActivation();
        assertEquals(22.0, activationSum);
    }

    @org.junit.jupiter.api.Test
    void calcSumNeg() {
        double sum = singleNeuron.calcSum(inputsNeg);
        assertEquals(-20.0, sum);
    }

    @org.junit.jupiter.api.Test
    void calcActivationDirectNeg() {
        double sum = singleNeuron.calcActivation(inputsNeg);
        assertEquals(-18.0, sum);
    }

    @org.junit.jupiter.api.Test
    void calcActivationIndirectNeg() {
        double sum = singleNeuron.calcSum(inputsNeg);
        assertEquals(-20.0, sum);

        double activationSum = singleNeuron.calcActivation();
        assertEquals(-18.0, activationSum);
    }
}
package neuralnetwork.neurons;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import neuralnetwork.activationfunctions.ActivationFunction;

@Data
@Slf4j
public class HiddenNeuron extends BasisNeuron {
    private double weightedSum = 0.0;
    private double bias;
    private double[] weights;

    private ActivationFunction activationFunction;

    public HiddenNeuron(double bias, double[] weights, ActivationFunction activationFunction) {
        this.weights = weights;
        this.bias = bias;
        this.activationFunction = activationFunction;
    }

    public double calcSum(double[] input)
    {
        if (input == null) {
            log.error("Input array most not be null!");
            return 0;
        }

        if (input.length < 1) {
            log.error("Input array most not be empty!");
            return 0;
        }

        if (weights == null) {
            log.error("Weights array most not be null!");
            return 0;
        }

        if (weights.length < 1) {
            log.error("Weights array most not be empty!");
            return 0;
        }

        int sizeInput = input.length;
        int sizeWeights = weights.length;

        if (sizeInput != sizeWeights) {
            log.warn("Size of weights and input array should be equal!");
            return 0;
        }

        double sum = 0.0;
        for (int i = 0; i < sizeWeights; i++)
        {
            sum += input[i] * weights[i];
        }

        setValue(sum);
        return getValue();
    }

    public double calcActivation(double[] input)
    {
        return activationFunction.activateSum(getBias() + calcSum(input));
    }

    public double calcActivation()
    {
        return activationFunction.activateSum(getBias() + getValue());
    }
}

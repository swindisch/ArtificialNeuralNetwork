package neuralnetwork.activationfunctions;

public class ActivationSigmoid implements ActivationFunction {

    public double activateSum(double Sum) {
        return 1 / (1 + Math.exp(-Sum));
    }
}

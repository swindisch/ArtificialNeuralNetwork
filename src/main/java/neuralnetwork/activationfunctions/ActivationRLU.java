package neuralnetwork.activationfunctions;

public class ActivationRLU implements ActivationFunction {

    public double activateSum(double Sum) {
        return Math.max(0, Sum);
    }
}
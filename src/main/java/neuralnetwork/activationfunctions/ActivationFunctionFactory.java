package neuralnetwork.activationfunctions;

public class ActivationFunctionFactory {
    public static ActivationFunction createFromName(ActivationEnum func) {

        switch (func) {
            case LINEAR:
                return new ActivationLinear();

            case RLU:
                return new ActivationRLU();

            case SIGMOID:
                return new ActivationSigmoid();

            default:
                return null;
        }
    }
}

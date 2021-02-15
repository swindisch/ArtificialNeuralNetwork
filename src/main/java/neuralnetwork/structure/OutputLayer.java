package neuralnetwork.structure;

import neuralnetwork.activationfunctions.ActivationEnum;

public class OutputLayer extends HiddenLayer {

    public OutputLayer(int size, int numWeights, ActivationEnum actFunc) {
        super(0, size, numWeights, actFunc);
    }
}

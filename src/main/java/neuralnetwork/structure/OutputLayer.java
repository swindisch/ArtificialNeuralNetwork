package neuralnetwork.structure;

import neuralnetwork.activationfunctions.ActivationEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OutputLayer extends HiddenLayer {
    static final Logger logger = LogManager.getLogger(OutputLayer.class.getName());

    public OutputLayer(int size, int numWeights, ActivationEnum actFunc) {
        super(0, size, numWeights, actFunc);
    }
}

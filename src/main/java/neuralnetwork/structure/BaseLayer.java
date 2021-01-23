package neuralnetwork.structure;

import neuralnetwork.neurons.BasisNeuron;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public abstract class BaseLayer<T extends BasisNeuron> {
    static final Logger logger = LogManager.getLogger(InputLayer.class.getName());

    protected int size;
    protected ArrayList<T> layer;

    public BaseLayer(int size) {
        this.size = size;
        this.layer = new ArrayList<>(size);
    }
}

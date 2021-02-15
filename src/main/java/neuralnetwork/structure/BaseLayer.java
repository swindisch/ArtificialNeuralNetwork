package neuralnetwork.structure;

import java.util.ArrayList;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import neuralnetwork.neurons.BasisNeuron;

@Slf4j
@Getter
public abstract class BaseLayer<T extends BasisNeuron> {

    protected int size;
    protected int numWeights = 0;
    protected ArrayList<T> layer;

    public BaseLayer(int size, int numWeights) {
        this.size = size;
        this.numWeights = numWeights;
        this.layer = new ArrayList<>(size);
    }

    public double getSingleOutputValue(int idx) {
        return layer.get(idx).getValue();
    }

    public abstract double[] getNeuronWeights(int idxNeuron);
}

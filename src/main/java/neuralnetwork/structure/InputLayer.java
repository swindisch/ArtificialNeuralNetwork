package neuralnetwork.structure;

import neuralnetwork.neurons.InputNeuron;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InputLayer extends BaseLayer<InputNeuron> {
    static final Logger logger = LogManager.getLogger(InputLayer.class.getName());

    public InputLayer(int size) {
        super(size);
    }

    @Override
    public double[] getNeuronWeights(int idxNeuron) {
        return new double[0];
    }

    // TODO implement validation method

    public void createLayer(double[] values) {
        if (size < 1) {
            logger.error("Size must be greater than 0!");
            return;
        }

        if (values == null) {
            logger.error("Values must not be null!");
            return;
        }

        if (values.length < 1) {
            logger.error("Empty value array not allow!");
            return;
        }

        if (layer == null) {
            logger.error("Input array must be created!");
            return;
        }

        layer.clear();

        for (int i = 0; i < size; i++) {
            layer.add(new InputNeuron(values[i]));
        }
    }

    public void createLayer() {
        if (size < 1) {
            logger.error("Size must be greater than 0!");
            return;
        }
        double[] values = new double[size];
        createLayer(values);
    }

    public double[] getOutputValues() {
        if (size < 1) {
            logger.error("Size must be greater than 0!");
        }

        double[] values = new double[size];

        int idx = 0;
        for (InputNeuron neuron : layer) {
            values[idx++] = neuron.getValue();
        }

        return values;
    }

    public void setInputValues(double[] values) {
        if (values.length != size) {
            logger.error("Length of layer and values must be equal!");
        }

        int idx = 0;
        for (InputNeuron neuron : layer) {
            neuron.setValue(values[idx++]);
        }
    }

    public String toString() {
        StringBuilder str =new StringBuilder();
        str.append("InputLayer with " + size + " features\n");
        str.append("Values [");

        int idx = 0;

        for (InputNeuron neuron : layer) {
            str.append(neuron.getValue() + " ");
        }
        str.deleteCharAt(str.length() - 1);
        str.append("]\n");
        return str.toString();
    }
}

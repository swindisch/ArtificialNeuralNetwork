package neuralnetwork.structure;

import neuralnetwork.activationfunctions.ActivationFunction;
import neuralnetwork.activationfunctions.ActivationFunctionFactory;
import neuralnetwork.activationfunctions.ActivationEnum;
import neuralnetwork.neurons.HiddenNeuron;
import neuralnetwork.neurons.InputNeuron;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Random;

public class HiddenLayer extends BaseLayer<HiddenNeuron> {
    static final Logger logger = LogManager.getLogger(HiddenLayer.class.getName());

    int idx = 0;
    int numWeights = 0;
    ActivationFunction actFunc = null;

    public HiddenLayer(int idx, int size, int numWeights, ActivationEnum actFunc)
    {
        super(size);
        this.idx = idx;
        this.numWeights = numWeights;
        this.actFunc = ActivationFunctionFactory.createFromName(actFunc);
    }

    // TODO implement validation method

    public void createLayer(double[][] weights, double[] bias) {
        if (size < 1) {
            logger.error("Size must be greater than 0!");
            return;
        }

        if (weights == null) {
            logger.error("Weights must not be null!");
            return;
        }

        if (weights.length < 1) {
            logger.error("Empty weights array not allow!");
            return;
        }

        if (layer == null) {
            logger.error("Input array must be created!");
            return;
        }

        if (bias == null) {
            logger.error("Bias must not be null!");
            return;
        }

        if (bias.length < 1) {
            logger.error("Empty bias array not allow!");
            return;
        }


        layer.clear();

        for (int i = 0; i < size; i++) {
            layer.add(new HiddenNeuron(bias[i], weights[i], actFunc));
        }
    }

    public void createLayer() {
        if (size < 1) {
            logger.error("Size must be greater than 0!");
            return;
        }

        double[][] weights = new double[size][numWeights];
        double[] bias = new double[size];

        Random r = new Random();

        for (int j = 0; j < size; j++) {
            bias[j] = r.nextDouble();

            for (int i = 0; i < numWeights; i++) {
                weights[j][i] = ((double) (r.nextInt(100 + 100) - 100)) / 100.0;
            }
        }

        createLayer(weights, bias);
    }

    public double[] getBiasValues() {
        if (size < 1) {
            logger.error("Size must be greater than 0!");
        }

        double[] values = new double[size];

        int idx = 0;
        for (HiddenNeuron neuron : layer) {
            values[idx++] = neuron.getBias();
        }

        return values;
    }

    public void setBiasValues(double[] bias) {
        if (bias.length != size) {
            logger.error("Length of layer and bias must be equal!");
        }

        int idx = 0;
        for (HiddenNeuron neuron : layer) {
            neuron.setBias(bias[idx++]);
        }
    }


    public double[][] getWeights() {
        if (size < 1) {
            logger.error("Size must be greater than 0!");
        }

        double[][] weights = new double[size][];

        int idx = 0;
        for (HiddenNeuron neuron : layer) {
            weights[idx++] = neuron.getWeights();
        }

        return weights;
    }

    public void setWeights(double[][] weights) {
        if (weights.length != size) {
            logger.error("Length of layer and weights must be equal!");
        }

        int idx = 0;
        for (HiddenNeuron neuron : layer) {
            neuron.setWeights(weights[idx++]);
        }
    }

    public double[] calcOutput(double[] input)
    {
        if (input == null) {
            logger.error("Input value must not be null!");
            return null;
        }

        if (input.length != numWeights) {
            logger.error("Length of input and weights must be equal!");
            return null;
        }

        double[] output = new double[size];

        int idx = 0;
        for (HiddenNeuron neuron: layer) {
            output[idx++] = neuron.calcActivation(input);
        }

        return output;
    }

    public String toString() {
        StringBuilder str =new StringBuilder();
        str.append("HiddenLayer " + idx + " with " + size + " neurons\n");

        str.append("Bias [");
        for (HiddenNeuron neuron : layer) {
            str.append(neuron.getBias() + " ");
        }
        str.deleteCharAt(str.length() - 1);
        str.append("]\n");

        str.append("Weights [");
        for (HiddenNeuron neuron : layer) {

            str.append(Arrays.toString(neuron.getWeights()) + " ");
        }
        str.deleteCharAt(str.length() - 1);
        str.append("]\n");

        return str.toString();
    }

    @Override
    public double[] getNeuronWeights(int idxNeuron) {
        double[] weights = layer.get(idxNeuron).getWeights();
        return weights;
    }
}

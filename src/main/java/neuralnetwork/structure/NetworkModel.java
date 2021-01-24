package neuralnetwork.structure;

import neuralnetwork.activationfunctions.ActivationEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class NetworkModel {
    static final Logger logger = LogManager.getLogger(NetworkModel.class.getName());

    int sizeInputLayer = 0;
    int[] sizesHiddenLayer = null;
    ActivationEnum[] actFuncHiddenLayer = null;
    int sizeOutputLayer = 0;
    ActivationEnum actFuncOutLayer = null;

    InputLayer inputLayer = null;
    ArrayList<HiddenLayer> hiddenLayers = new ArrayList<>();
    OutputLayer outputLayer = null;

    ArrayList<BaseLayer> layers = new ArrayList<>();


    public NetworkModel(int sizeInputLayer, int[] sizesHiddenLayer, ActivationEnum[] actFuncHiddenLayer, int sizeOutputLayer, ActivationEnum actFuncOutLayer) {
        this.sizeInputLayer = sizeInputLayer;
        this.sizesHiddenLayer = sizesHiddenLayer;
        this.actFuncHiddenLayer = actFuncHiddenLayer;
        this.sizeOutputLayer = sizeOutputLayer;
        this.actFuncOutLayer = actFuncOutLayer;
    }

    public NetworkModel(int sizeInputLayer, int sizeOutputLayer, ActivationEnum actFuncOutLayer) {
        this.sizeInputLayer = sizeInputLayer;
        this.sizesHiddenLayer = new int[]{};
        this.actFuncHiddenLayer = null;
        this.sizeOutputLayer = sizeOutputLayer;
        this.actFuncOutLayer = actFuncOutLayer;
    }


    public void createModel() {
        layers.clear();

        inputLayer = new InputLayer(sizeInputLayer);
        inputLayer.createLayer();
        layers.add(inputLayer);

        hiddenLayers.clear();
        int sizePrevLayer = sizeInputLayer;

        for (int idx = 0; idx < sizesHiddenLayer.length; idx++) {
            HiddenLayer layer = new HiddenLayer(idx + 1, sizesHiddenLayer[idx], sizePrevLayer, actFuncHiddenLayer[idx]);
            layer.createLayer();
            hiddenLayers.add(layer);
            layers.add(layer);
            sizePrevLayer = sizesHiddenLayer[idx];
        }

        outputLayer = new OutputLayer(sizeOutputLayer, sizePrevLayer, actFuncOutLayer);
        outputLayer.createLayer();
        layers.add(outputLayer);
    }

    public double[] feedforward(double[] input) {
        inputLayer.setInputValues(input);
        double[] output = inputLayer.getOutputValues();

        for (HiddenLayer layer: hiddenLayers) {
            output = layer.calcOutput(output);
        }

        output = outputLayer.calcOutput(output);

        return output;
    }

    public int getNumLayers() {
        return sizesHiddenLayer.length + 2;
    }

    public int getLayerSize(int idxLayer) {
        return layers.get(idxLayer).size;

        /*if (idxLayer == 0)
            return inputLayer.size;

        if (idxLayer > sizesHiddenLayer.length)
            return outputLayer.size;

        return sizesHiddenLayer[idxLayer - 1];*/
    }

    public double getValue(int idxLayer, int idxNeuron) {
        return layers.get(idxLayer).getSingleOutputValue(idxNeuron);

        /*if (idxLayer == 0)
            return inputLayer.getSingleOutputValue(idxNeuron);

        if (idxLayer > sizesHiddenLayer.length)
            return outputLayer.getSingleOutputValue(idxNeuron);

        return hiddenLayers.get(idxLayer - 1).getSingleOutputValue(idxNeuron);
    */}

    public double[] getWeights(int idxLayer, int idxNeuron) {
        return layers.get(idxLayer).getNeuronWeights(idxNeuron);
    }
}

package neuralnetwork.structure;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import neuralnetwork.activationfunctions.ActivationEnum;

@Slf4j
public class NetworkModel {
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


    public void createModel(boolean setRandom, boolean setDefault, double defaultValue) {
        layers.clear();

        inputLayer = new InputLayer(sizeInputLayer);
        inputLayer.createLayer();
        layers.add(inputLayer);

        hiddenLayers.clear();
        int sizePrevLayer = sizeInputLayer;

        for (int idx = 0; idx < sizesHiddenLayer.length; idx++) {
            HiddenLayer layer = new HiddenLayer(idx + 1, sizesHiddenLayer[idx], sizePrevLayer, actFuncHiddenLayer[idx]);
            layer.createLayer(setRandom, setDefault, defaultValue);
            hiddenLayers.add(layer);
            layers.add(layer);
            sizePrevLayer = sizesHiddenLayer[idx];
        }

        outputLayer = new OutputLayer(sizeOutputLayer, sizePrevLayer, actFuncOutLayer);
        outputLayer.createLayer(setRandom, setDefault, defaultValue);
        layers.add(outputLayer);
    }

    public void createModel(InputLayer sourceInputLayer, ArrayList<HiddenLayer> sourceHiddenLayers, OutputLayer sourceOutputLayer) {
        layers.clear();

        inputLayer = new InputLayer(sizeInputLayer);
        inputLayer.createLayer(sourceInputLayer.getOutputValues());
        layers.add(inputLayer);

        hiddenLayers.clear();
        int sizePrevLayer = sizeInputLayer;

        for (int idx = 0; idx < sizesHiddenLayer.length; idx++) {
            HiddenLayer layer = new HiddenLayer(idx + 1, sizesHiddenLayer[idx], sizePrevLayer, actFuncHiddenLayer[idx]);
            layer.createLayer(sourceHiddenLayers.get(idx).getWeights(), sourceHiddenLayers.get(idx).getBiasValues());
            hiddenLayers.add(layer);
            layers.add(layer);
            sizePrevLayer = sizesHiddenLayer[idx];
        }

        outputLayer = new OutputLayer(sizeOutputLayer, sizePrevLayer, actFuncOutLayer);
        outputLayer.createLayer(sourceOutputLayer.getWeights(), sourceOutputLayer.getBiasValues());
        layers.add(outputLayer);
    }


    public NetworkModel cloneModel() {

        NetworkModel newModel = new NetworkModel(sizeInputLayer, sizesHiddenLayer, actFuncHiddenLayer, sizeOutputLayer, actFuncOutLayer);
        newModel.createModel(inputLayer, hiddenLayers, outputLayer);

        return newModel;
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


    private int getDNASize() {
        int dnaSize = 0;

        for (BaseLayer layer: layers) {
            if (layer instanceof InputLayer) continue;
            HiddenLayer hlayer = (HiddenLayer) layer;

            // number of bias
            dnaSize += hlayer.getSize();
            // number of weights
            dnaSize += hlayer.getNumWeights() * hlayer.getSize();
        }

        return dnaSize;
    }


    public double[] getDNA() {

        double[] dna;
        int dnaSize = getDNASize();
        dna = new double[dnaSize];

        int offset = 0;

        for (BaseLayer layer: layers) {
            if (layer instanceof InputLayer) continue;
            HiddenLayer hlayer = (HiddenLayer) layer;

            System.arraycopy(hlayer.getBiasValues(), 0, dna, offset, hlayer.getSize());
            offset += hlayer.getSize();

            for (int i = 0; i < hlayer.getSize(); i++) {
                System.arraycopy(hlayer.getNeuronWeights(i), 0, dna, offset, hlayer.getNumWeights());
                offset += hlayer.getNumWeights();
            }
        }
        return dna;
    }

    public void setDNA(double[] dna) {

        int dnaSize = getDNASize();
        int offset = 0;

        if (dnaSize != dna.length) {
            throw new InvalidParameterException(String.format("dnaSize %d and dna.length %d different", dnaSize, dna.length));
        }

        for (BaseLayer layer: layers) {
            if (layer instanceof InputLayer) continue;
            HiddenLayer hlayer = (HiddenLayer) layer;

            double[] bias = new double[hlayer.getSize()];
            System.arraycopy(dna, offset, bias, 0, hlayer.getSize());
            hlayer.setBiasValues(bias);

            offset += hlayer.getSize();

            for (int i = 0; i < hlayer.getSize(); i++) {
                double[] weights = new double[hlayer.getNumWeights()];
                System.arraycopy(dna, offset, weights, 0, hlayer.getNumWeights());
                hlayer.setNeuronWeights(i, weights);
                offset += hlayer.getNumWeights();
            }
        }
    }
}

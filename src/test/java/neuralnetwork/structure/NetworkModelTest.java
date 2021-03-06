package neuralnetwork.structure;

import neuralnetwork.activationfunctions.ActivationEnum;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class NetworkModelTest {

    @Test
    void createPerceptron() {

        NetworkModel model = new NetworkModel(4, 1, ActivationEnum.LINEAR);
        model.createModel(false, true, 1.0);

        double[] output = model.feedforward(new double[]{1, 2, 3, 4});
        System.out.println((Arrays.toString(output)));
    }

    @Test
    void createMultiLayerPerceptron() {

        int[] hiddenLayers = new int[2];
        hiddenLayers[0] = 256;
        hiddenLayers[1] = 128;

        ActivationEnum[] hiddenAcFun = new ActivationEnum[2];
        hiddenAcFun[0] = ActivationEnum.LINEAR;
        hiddenAcFun[1] = ActivationEnum.LINEAR;

        NetworkModel model = new NetworkModel(28*28, hiddenLayers, hiddenAcFun, 1, ActivationEnum.LINEAR);
        model.createModel(false, true, 1.0);

        double[] inputPic = new double[28*28];

        Random r = new Random(42);


        for (int idx = 0; idx < inputPic.length; idx++) {
            inputPic[idx] = r.nextDouble();
        }

        double[] output = model.feedforward(inputPic);
        System.out.println((Arrays.toString(output)));
    }

    @Test
    void cloneModel() {
        int[] hiddenLayers = new int[2];
        hiddenLayers[0] = 4;
        hiddenLayers[1] = 6;

        ActivationEnum[] hiddenAcFun = new ActivationEnum[2];
        hiddenAcFun[0] = ActivationEnum.LINEAR;
        hiddenAcFun[1] = ActivationEnum.LINEAR;

        NetworkModel model = new NetworkModel(4, hiddenLayers, hiddenAcFun, 2, ActivationEnum.LINEAR);
        model.createModel(true, false, 0.0);

        double[] inputPic = new double[4];
        Random r = new Random(42);
        for (int idx = 0; idx < inputPic.length; idx++) {
            inputPic[idx] = r.nextDouble();
        }
        NetworkModel clonedModel = model.cloneModel();

        model.feedforward(inputPic);
        clonedModel.feedforward(inputPic);

        int sourceNumLayers = model.getNumLayers();
        int clonedNumLayers = clonedModel.getNumLayers();

        assertEquals(sourceNumLayers, clonedNumLayers);

        for (int idxLayer = 0; idxLayer < sourceNumLayers; idxLayer++) {

            int sourceNumNeurons = model.getLayerSize(idxLayer);
            int clonedNumNeurons = clonedModel.getLayerSize(idxLayer);

            assertEquals(sourceNumNeurons, clonedNumNeurons);

            for (int idxNeuron = 0; idxNeuron < sourceNumNeurons; idxNeuron++) {
                double sourceValue = model.getValue(idxLayer, idxNeuron);
                double clonedValue = clonedModel.getValue(idxLayer, idxNeuron);

                assertEquals(sourceValue, clonedValue);

                double[] sourceWeights = model.getWeights(idxLayer, idxNeuron);
                double[] clonedWeights = clonedModel.getWeights(idxLayer, idxNeuron);

                assertArrayEquals(sourceWeights, clonedWeights);
            }
        }
    }

    @Test
    void getDNA() {

        int[] hiddenLayers = new int[2];
        hiddenLayers[0] = 4;
        hiddenLayers[1] = 6;

        ActivationEnum[] hiddenAcFun = new ActivationEnum[2];
        hiddenAcFun[0] = ActivationEnum.LINEAR;
        hiddenAcFun[1] = ActivationEnum.LINEAR;

        NetworkModel model = new NetworkModel(4, hiddenLayers, hiddenAcFun, 2, ActivationEnum.LINEAR);
        model.createModel(false, true, 1.0);

        double[] dna = model.getDNA();

        assertEquals(64, dna.length);

        for (int i = 0; i < 64; i++) {
            assertEquals(1.0, dna[i]);
        }

    }

    @Test
    void setDNA() {

        int[] hiddenLayers = new int[2];
        hiddenLayers[0] = 4;
        hiddenLayers[1] = 6;

        ActivationEnum[] hiddenAcFun = new ActivationEnum[2];
        hiddenAcFun[0] = ActivationEnum.LINEAR;
        hiddenAcFun[1] = ActivationEnum.LINEAR;

        NetworkModel model = new NetworkModel(4, hiddenLayers, hiddenAcFun, 2, ActivationEnum.LINEAR);
        model.createModel(false, false, 0.0);

        double[] inputDNA = new double[64];

        for (int i = 0; i < 64; i++) {
            inputDNA[i] = 64 - i;
        }

        model.setDNA(inputDNA);

        double[] outputDNA = model.getDNA();

        assertEquals(64, outputDNA.length);
        assertArrayEquals(inputDNA, outputDNA);

    }
}

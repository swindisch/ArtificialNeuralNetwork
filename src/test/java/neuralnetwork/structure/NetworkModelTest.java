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
        model.createModel();

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
        model.createModel();

        double[] inputPic = new double[28*28];

        Random r = new Random(42);


        for (int idx = 0; idx < inputPic.length; idx++) {
            inputPic[idx] = r.nextDouble();
        }

        double[] output = model.feedforward(inputPic);
        System.out.println((Arrays.toString(output)));
    }

}
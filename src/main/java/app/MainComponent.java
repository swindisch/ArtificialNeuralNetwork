package app;

import neuralnetwork.activationfunctions.ActivationEnum;
import neuralnetwork.structure.NetworkModel;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class MainComponent extends JComponent {

    NetworkModel model;

    public MainComponent() {
        int[] hiddenLayers = new int[2];
        hiddenLayers[0] = 8;
        hiddenLayers[1] = 4;

        ActivationEnum[] hiddenAcFun = new ActivationEnum[2];
        hiddenAcFun[0] = ActivationEnum.SIGMOID;
        hiddenAcFun[1] = ActivationEnum.SIGMOID;

        model = new NetworkModel(4, hiddenLayers, hiddenAcFun, 1, ActivationEnum.LINEAR);
        model.createModel();

        double[] inputPic = new double[4];
        Random r = new Random(42);

        for (int idx = 0; idx < inputPic.length; idx++) {
            inputPic[idx] = ((double) (r.nextInt(100 + 100) - 100)) / 100.0;
        }
        model.feedforward(inputPic);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());

        System.out.println("repaint");
        DisplayNetwork.show(model, g, getWidth(), getHeight());
    }
}

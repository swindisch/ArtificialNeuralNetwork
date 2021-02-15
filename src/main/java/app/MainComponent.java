package app;

import neuralnetwork.activationfunctions.ActivationEnum;
import neuralnetwork.structure.NetworkModel;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class MainComponent extends JComponent {

    NetworkModel model;
    public static final long SimulationTick = 100;

    public MainComponent() {
        int[] hiddenLayers = new int[2];
        hiddenLayers[0] = 8;
        hiddenLayers[1] = 4;

        ActivationEnum[] hiddenAcFun = new ActivationEnum[2];
        hiddenAcFun[0] = ActivationEnum.SIGMOID;
        hiddenAcFun[1] = ActivationEnum.SIGMOID;

        model = new NetworkModel(4, hiddenLayers, hiddenAcFun, 2, ActivationEnum.LINEAR);
        model.createModel(true, false, 0.0);

        new Thread(new Runnable() {
            private long simulationLastMillis;

            public void run() {
                simulationLastMillis = System.currentTimeMillis() + 100; // initial
                double[] inputPic = new double[4];
                Random r = new Random(42);

                while (true) {
                    if (System.currentTimeMillis() - simulationLastMillis > SimulationTick) {
                        synchronized (model) {
                            for (int idx = 0; idx < inputPic.length; idx++) {
                                inputPic[idx] = ((double) (r.nextInt(100 + 100) - 100)) / 100.0;
                            }
                            model.createModel(true, false, 0.0);
                            model.feedforward(inputPic);

                            repaint();
                            simulationLastMillis += SimulationTick;
                        }
                    }
                }
            }
        }).start();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());

        synchronized (model) {
            DisplayNetwork.show(model, g, getWidth(), getHeight());
        }
    }
}

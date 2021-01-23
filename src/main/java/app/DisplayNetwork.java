package app;

import neuralnetwork.structure.NetworkModel;

import java.awt.*;

public class DisplayNetwork {

    public static void show(NetworkModel model, Graphics g, int width, int height) {
        showWeights(model, g, width, height);
        showNeurons(model, g, width, height);
    }

    public static void showWeights(NetworkModel model, Graphics g, int width, int height) {
        int numLayers = model.getNumLayers();

        for (int idxLayer = 0; idxLayer < numLayers - 1; idxLayer++) {
            int xSize = (int) (width / (numLayers + 1));
            int xPosStart = (idxLayer + 1) * xSize;
            int xPosEnd = (idxLayer + 2) * xSize;

            int numNeuronsStart = model.getLayerSize(idxLayer);
            int numNeuronsEnd = model.getLayerSize(idxLayer + 1);

            for (int idxNeuronStart = 0; idxNeuronStart < numNeuronsStart; idxNeuronStart++) {
                for (int idxNeuronEnd = 0; idxNeuronEnd < numNeuronsEnd; idxNeuronEnd++) {


                    int ySizeStart = (int) (height / (numNeuronsStart + 1));
                    int ySizeEnd = (int) (height / (numNeuronsEnd + 1));

                    int yPosStart = (idxNeuronStart + 1) * ySizeStart;
                    int yPosEnd =  (idxNeuronEnd + 1) * ySizeEnd;

                    double value = 1;
                    Color col;

                    if (value > 0.0)
                        col = new Color(255,150,150);
                    else
                        col = new Color(150, 255, 150);

                    g.setColor(col);
                    g.drawLine(xPosStart, yPosStart, xPosEnd, yPosEnd);
                }
            }
        }
    }


    public static void showNeurons(NetworkModel model, Graphics g, int width, int height) {
        int numLayers = model.getNumLayers();

        for (int idxLayer = 0; idxLayer < numLayers; idxLayer++) {
            int xSize = (int) (width / (numLayers + 1));
            int xPos = (idxLayer + 1) * xSize;

            int numNeurons = model.getLayerSize(idxLayer);

            int radius = 5 + (height / (numNeurons * 3));
            if (radius > 50) radius = 50;

            for (int idxNeuron = 0; idxNeuron < numNeurons; idxNeuron++) {
                int ySize = (int) (height / (numNeurons + 1));
                int yPos = (idxNeuron + 1) * ySize;

                double value = model.getValue(idxLayer, idxNeuron);
                Color col;

                if (value > 0.0)
                    col = new Color(255,150,150);
                else
                    col = new Color(150, 255, 150);

                g.setColor(col);
                g.fillOval(xPos - radius, yPos - radius, radius * 2, radius * 2);

                g. setFont( new Font( "Verdana", Font.BOLD, 20 ) );
                g.setColor(Color.BLACK);
                g.drawString(String.format("%.2f", value), xPos - 20, yPos + 10);
            }
        }
    }
}

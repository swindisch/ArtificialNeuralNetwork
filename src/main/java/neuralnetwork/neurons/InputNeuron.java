package neuralnetwork.neurons;

import lombok.Data;

@Data
public class InputNeuron extends BasisNeuron {

    public InputNeuron(double value) {
        this.setValue(value);
    }
}

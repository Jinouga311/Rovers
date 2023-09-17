package org.rovers;

import java.io.IOException;
import java.util.List;

public interface InputProvider {
    List<RoverInstructions> getInstructions() throws IOException;
}

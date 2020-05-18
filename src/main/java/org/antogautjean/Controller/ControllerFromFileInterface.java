package org.antogautjean.Controller;

import java.io.IOException;

public interface ControllerFromFileInterface {
    void refreshFromFile() throws IOException;
    void setIfFileImportFailed(boolean b);
    boolean getIfFileImportFailed();
}
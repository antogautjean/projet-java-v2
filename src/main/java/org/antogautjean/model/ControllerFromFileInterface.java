package org.antogautjean.model;

import java.io.IOException;

public interface ControllerFromFileInterface {
    public void refreshFromFile() throws IOException;
    public void setIfFileImportFailed(boolean b);
    public boolean getIfFileImportFailed();
}
package org.antogautjean.controller;

import java.io.IOException;

public interface ControllerFromFileInterface {
    void refreshFromFile() throws IOException;
    void setIfFileImportFailed(boolean b);
    boolean getIfFileImportFailed();
}
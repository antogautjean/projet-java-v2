package org.antogautjean.view;

import org.antogautjean.Controller.LogController;

import java.text.DateFormat;
import java.util.Date;

public class LogView {

    private DateFormat dateFormat;
    private Date date;

    static void logger(boolean USE){

        AppRunner.LOGGER = new LogController(".", USE);
    }

}

package org.antogautjean.view;

import org.antogautjean.Controller.LogController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogView {

    private DateFormat dateFormat;
    private Date date;

    static void logger(boolean USE){

        App.LOGGER = new LogController(".", USE);
    }

    private void err_log(Integer code, String who, String what, String info){
        System.err.println(who + " - - [" + dateFormat.format(date) + "] " + what + " ( " + info + " )");
    }

    private void norm_log(Integer code, String who, String what, String info){
        System.out.println(who + " - - [" + dateFormat.format(date) + "] " + what + " ( " + info + " )");
    }

}

package org.antogautjean.Controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;

public class LogController {

    private String logPath;
    private DateFormat dateFormat;
    private Date date;

    private boolean ACTIVATED;

    public LogController(String logPath, boolean ACTIVATED){

        this.dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        this.date = new Date();
        this.ACTIVATED = ACTIVATED;
        this.logPath = logPath;
    }

    // "GUI - - [18/03/2020 12:55:47] Factory controler::addToStock";

    private void err_log(Integer code, String who, String what, String info){
        System.err.println(who + " - - [" + dateFormat.format(date) + "] " + what + " ( " + info + " )");
    }

    private void norm_log(Integer code, String who, String what, String info){
        System.out.println(who + " - - [" + dateFormat.format(date) + "] " + what + " ( " + info + " )");
    }

    private void action_log(String who, String what, String info){
        //TODO : Event maj interface history
    }

    public void log(Integer code, String who, String what, String info){

        if (this.ACTIVATED){

            //TODO: Log into file / Interface history
            switch (code){
                case 1:
                    action_log(who, what, info);
                    break;
                case 0:
                    norm_log(code, who, what, info);
                    break;
                case -1:
                    err_log(code, who, what, info);
                    break;
            }
        }
    }
}

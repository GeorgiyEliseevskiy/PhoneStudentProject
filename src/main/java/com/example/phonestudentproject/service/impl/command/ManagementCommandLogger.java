package com.example.phonestudentproject.service.impl.command;

import com.example.phonestudentproject.service.api.command.CommandLogger;

public class ManagementCommandLogger {
    CommandLogger warn;
    CommandLogger debug;
    CommandLogger error;
    CommandLogger info;

    public ManagementCommandLogger(CommandLogger warn, CommandLogger debug, CommandLogger error, CommandLogger info) {
        this.warn = warn;
        this.debug = debug;
        this.error = error;
        this.info = info;
    }

    public void warnMessage(String msg) {
        warn.execute(msg);
    }

    public void debugMessage(String msg) {
        debug.execute(msg);
    }

    public void errorMessage(String msg) {
        error.execute(msg);
    }

    public void infoMessage(String msg) {
        info.execute(msg);
    }


}

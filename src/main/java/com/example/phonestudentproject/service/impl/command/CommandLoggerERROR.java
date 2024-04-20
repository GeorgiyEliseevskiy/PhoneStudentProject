package com.example.phonestudentproject.service.impl.command;

import com.example.phonestudentproject.service.api.command.CommandLogger;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommandLoggerERROR implements CommandLogger {
    @Override
    public void execute(String msg) {
        log.error(msg);
    }
}

package com.dh.catalog.exceptions;

import org.apache.logging.log4j.message.Message;

public class MovieExceptions extends Exception{
    public MovieExceptions (MessageErrors message) {
        super(String.valueOf(message));
    }
}

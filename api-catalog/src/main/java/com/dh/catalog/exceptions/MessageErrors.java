package com.dh.catalog.exceptions;

public enum MessageErrors {
    CATALOG_HASNOT_MOVIE("MOVIE NOT FOUND");
    String message;

    MessageErrors(String message) {
        this.message = message;
    }
}

package com.swisscom.example;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum State {

    DELIVER("Deliver"),
    CANCEL("Cancel"),
    RETURN("Return"),
    ORDER("Order");

    private final String name;
}

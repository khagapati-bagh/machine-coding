package com.cab.models;

import com.cab.enums.CabType;
import lombok.Data;

public class Driver extends User{
    private CabType cabType;

    public Driver(String name) {
        super(name);
    }

}

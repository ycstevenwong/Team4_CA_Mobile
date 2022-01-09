package com.example.team4_ca_mobile;

import java.io.Serializable;

public class SFX implements Serializable {
    private final String fname;

    public SFX(String fname) {
        this.fname = fname;
    }

    public String getFname() { return fname; }
}

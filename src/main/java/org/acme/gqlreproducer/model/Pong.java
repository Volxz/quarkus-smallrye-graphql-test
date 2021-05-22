package org.acme.gqlreproducer.model;

public class Pong {
    private int pongNumber;

    public Pong(int pongNumber) {
        this.pongNumber = pongNumber;
    }

    public Pong() {
    }
    public int getPongNumber() {
        return pongNumber;
    }

    public void setPongNumber(int pongNumber) {
        this.pongNumber = pongNumber;
    }

}

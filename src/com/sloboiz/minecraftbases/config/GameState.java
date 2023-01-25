package com.sloboiz.minecraftbases.config;

public class GameState
{
    private Cores cores;

    private Phase phase;

    public Cores getCores() {
        return cores;
    }

    public void setCores(Cores cores) {
        this.cores = cores;
    }

    public Phase getPhase() {
        return phase;
    }

    public void setPhase(Phase phase) {
        this.phase = phase;
    }
}

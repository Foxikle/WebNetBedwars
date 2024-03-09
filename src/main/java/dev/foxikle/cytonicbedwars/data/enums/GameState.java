package dev.foxikle.cytonicbedwars.data.enums;

public enum GameState {
    WAITING(-1), // not yet started
    PLAY(900), // 15 minutes
    DEATHMATCH(300), // 5 minutes
    SUDDEN_DEATH(180), // withers - 3 minutes
    ENDED(30), // 30 sec
    CLEANUP(-1),

    // admin things
    FROZEN(-1);

    // in seconds
    private final int duration;

    GameState(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }
}

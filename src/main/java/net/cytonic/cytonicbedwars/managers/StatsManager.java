package net.cytonic.cytonicbedwars.managers;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class StatsManager {
    private final Map<UUID, Integer> playerKills = new HashMap<>();
    private final Map<UUID, Integer> playerDeaths = new HashMap<>();
    private final Map<UUID, Integer> playerFinalKills = new HashMap<>();
    private final Map<UUID, Integer> playerBedsBroken = new HashMap<>();

    private final Map<UUID, Double> playerDamangeDealt = new HashMap<>();
    private final Map<UUID, Double> playerDamangeTaken = new HashMap<>();

    /**
     * Adds the player's uuid to the maps with empty values (0)
     * @param uuid The player to propagate values for
     */
    public void propagatePlayer(UUID uuid){
        playerKills.put(uuid, 0);
        playerDeaths.put(uuid, 0);
        playerFinalKills.put(uuid, 0);
        playerBedsBroken.put(uuid, 0);

        playerDamangeDealt.put(uuid, 0.0);
        playerDamangeTaken.put(uuid, 0.0);
    }

    /**
     * Gets the amount of vanilla minecraft damage a player has dealt.
     * @param uuid the player whose damage should be gotten
     * @return the damage the player has dealt
     */
    public double getPlayerDamageDealt(UUID uuid){
        return playerDamangeDealt.get(uuid);
    }

    /**
     * Gets the amount of vanilla minecraft damage a player has taken.
     * @param uuid the player whose damage should be gotten
     * @return the damage the player has taken
     */
    public double getPlayerDamageTaken(UUID uuid){
        return playerDamangeTaken.get(uuid);
    }

    /**
     * Adds to the total player damage
     * @param uuid The player to add damage to
     * @param ammount The amount of damage to add to the player
     * @return the new total damage
     */
    public double addPlayerDamageDealt(UUID uuid, double ammount){
        return playerDamangeDealt.put(uuid, playerDamangeDealt.get(uuid) + ammount);
    }

    /**
     * Adds to the total player damage
     * @param uuid The player to add damage to
     * @param ammount The amount of damage to add to the player
     * @return the new total damage
     */
    public double addPlayerDamageTaken(UUID uuid, double ammount){
        return playerDamangeTaken.put(uuid, playerDamangeTaken.get(uuid) + ammount);
    }

    public int addPlayerKill(UUID uuid){
        return playerKills.put(uuid, playerKills.get(uuid) + 1);
    }

    public int addPlayerFinalKill(UUID uuid){
        return playerFinalKills.put(uuid, playerFinalKills.get(uuid) + 1);
    }

    public int addPlayerDeath(UUID uuid){
        return playerDeaths.put(uuid, playerDeaths.get(uuid) + 1);
    }

    public int addPlayerBedBroken(UUID uuid){
        return playerBedsBroken.put(uuid, playerBedsBroken.get(uuid) + 1);
    }


    public int getPlayerKills(UUID uuid){
        return playerKills.put(uuid, playerKills.get(uuid) + 1);
    }

    public int getPlayerFinalKills(UUID uuid){
        return playerFinalKills.put(uuid, playerFinalKills.get(uuid) + 1);
    }

    public int getPlayerDeaths(UUID uuid){
        return playerDeaths.put(uuid, playerDeaths.get(uuid) + 1);
    }

    public int getPlayerBedsBroken(UUID uuid){
        return playerBedsBroken.put(uuid, playerBedsBroken.get(uuid) + 1);
    }

}

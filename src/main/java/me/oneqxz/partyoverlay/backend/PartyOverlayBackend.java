package me.oneqxz.partyoverlay.backend;

import me.oneqxz.partyoverlay.backend.network.ServerConnection;

/**
 * PartyOverlayClientBackend
 *
 * @author oneqxz
 * @since 17.04.2024
 */
public final class PartyOverlayBackend {

    private static PartyOverlayBackend INSTANCE;

    public void init()
    {
        ServerConnection.connect();
    }

    public static PartyOverlayBackend getInstance()
    {
        return INSTANCE == null ? INSTANCE = new PartyOverlayBackend() : INSTANCE;
    }

}

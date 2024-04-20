package me.oneqxz.partyoverlay.backend.listeners;

import java.util.UUID;

/**
 * PartyOverlayClientBackend
 *
 * @author oneqxz
 * @since 17.04.2024
 */
public interface IEventListener {

    void onInvalidCreditsDisconnect();
    void onAlreadyConnectedDisconnect();
    void onServerErrorDisconnect();

    void onConnect(UUID sessionUUID, String username);

}

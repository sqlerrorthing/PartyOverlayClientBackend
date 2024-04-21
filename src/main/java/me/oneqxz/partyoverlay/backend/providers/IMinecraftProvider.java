package me.oneqxz.partyoverlay.backend.providers;

/**
 * PartyOverlayClientBackend
 *
 * @author oneqxz
 * @since 17.04.2024
 */
public interface IMinecraftProvider {

    String getMinecraftVersion();
    String getCurrentServer();

}

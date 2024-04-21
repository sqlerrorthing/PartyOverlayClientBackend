package me.oneqxz.partyoverlay.backend.providers;

/**
 * PartyOverlayClientBackend
 *
 * @author oneqxz
 * @since 21.04.2024
 */
public interface IPlayerProvider {

    boolean nonNull();

    float getHealth();
    float getMaxHealth();

    double getX();
    double getY();
    double getZ();

    float getYaw();
    float getPitch();
}

package me.oneqxz.partyoverlay.backend.providers;

import me.oneqxz.partyoverlay.backend.sctructures.WrappedItemStack;

import java.io.InputStream;

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

    int getHurtTime();

    InputStream getSkinInputStream();

    WrappedItemStack getMainHandItem();
    WrappedItemStack getOffHandItem();

    WrappedItemStack getHelmetItem();
    WrappedItemStack getChestplateItem();
    WrappedItemStack getLeggingsItem();
    WrappedItemStack getBootsItem();
}

package me.oneqxz.partyoverlay.backend.listeners;

import me.oneqxz.partyoverlay.backend.PartyOverlayBackend;
import me.oneqxz.partyoverlay.backend.network.ServerConnection;
import me.oneqxz.partyoverlay.backend.network.protocol.packets.c2s.CMinecraftUsernameChanged;

/**
 * PartyOverlayClientBackend
 *
 * @author oneqxz
 * @since 21.04.2024
 */
public class InternalEvents {

    public void onMinecraftUsernameChange(String newUsername)
    {
        if(PartyOverlayBackend.getInstance().isConnected())
            ServerConnection.getInstance().getConnection().channel().writeAndFlush(new CMinecraftUsernameChanged(newUsername));
    }

}

package me.oneqxz.partyoverlay.backend.utils;

import me.oneqxz.partyoverlay.backend.PartyOverlayBackend;
import me.oneqxz.partyoverlay.backend.network.ServerConnection;
import me.oneqxz.partyoverlay.backend.network.protocol.Packet;

/**
 * PartyOverlayClientBackend
 *
 * @author oneqxz
 * @since 21.04.2024
 */
public class ConnectionUtils {

    public static void sendPacketIfConnected(Packet packet)
    {
        if(PartyOverlayBackend.getInstance().isConnected())
            ServerConnection.getInstance().getConnection().channel().writeAndFlush(packet);
    }

}

package me.oneqxz.partyoverlay.backend.listeners;

import me.oneqxz.partyoverlay.backend.network.protocol.packets.c2s.CMinecraftUsernameChanged;
import me.oneqxz.partyoverlay.backend.network.protocol.packets.c2s.CStartPlaying;
import me.oneqxz.partyoverlay.backend.network.protocol.packets.c2s.CStopPlaying;
import me.oneqxz.partyoverlay.backend.sctructures.ServerData;
import me.oneqxz.partyoverlay.backend.utils.ConnectionUtils;

/**
 * PartyOverlayClientBackend
 *
 * @author oneqxz
 * @since 21.04.2024
 */
public class InternalEvents {

    public void onMinecraftUsernameChange(String newUsername)
    {
        ConnectionUtils.sendPacketIfConnected(new CMinecraftUsernameChanged(newUsername));
    }

    public void onPlayerStartPlaying(String serverIP)
    {
        ConnectionUtils.sendPacketIfConnected(new CStartPlaying(
                ServerData.builder()
                    .serverIP(serverIP)
                    .build()
        ));
    }

    public void onPlayerStopPlaying()
    {
        ConnectionUtils.sendPacketIfConnected(new CStopPlaying());
    }
}

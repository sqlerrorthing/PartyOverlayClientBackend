package me.oneqxz.partyoverlay.backend.listeners;

import me.oneqxz.partyoverlay.backend.PartyOverlayBackend;
import me.oneqxz.partyoverlay.backend.manager.PartyManager;
import me.oneqxz.partyoverlay.backend.network.protocol.packets.c2s.*;
import me.oneqxz.partyoverlay.backend.sctructures.ServerData;
import me.oneqxz.partyoverlay.backend.utils.ConnectionUtils;

import java.util.UUID;

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

    public void removeFriend(int friendID)
    {
        ConnectionUtils.sendPacketIfConnected(new CFriendRemove(friendID));
    }

    public void inviteFriendToParty(int friendID)
    {
        ConnectionUtils.sendPacketIfConnected(new CFriendPartyInvite(friendID));
    }

    public void onPartyLeave()
    {
        ConnectionUtils.sendPacketIfConnected(new CPartyLeave());

        if(PartyManager.getInstance().isOnParty())
            PartyOverlayBackend.getInstance().getListener().onPartyLeaved(PartyManager.getInstance().getPartyUUID());

        PartyManager.getInstance().reset();
    }

    public void onPartyInviteAccept(UUID partyUUID)
    {
        ConnectionUtils.sendPacketIfConnected(new CPartyInviteAccept(partyUUID));
    }

    public void onPartyInviteRejected(UUID partyUUID)
    {
        ConnectionUtils.sendPacketIfConnected(new CPartyInviteReject(partyUUID));
    }

    public void onFriendRequestSend(String username)
    {
        ConnectionUtils.sendPacketIfConnected(new CFriendRequest(username));
    }

    public void acceptFriendRequest(String username)
    {
        ConnectionUtils.sendPacketIfConnected(new CAcceptFriendRequest(username));
    }

    public void rejectFriendRequest(String username)
    {
        ConnectionUtils.sendPacketIfConnected(new CRejectFriendRequest(username));
    }
}

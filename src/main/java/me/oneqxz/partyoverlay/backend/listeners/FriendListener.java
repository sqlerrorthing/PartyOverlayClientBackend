package me.oneqxz.partyoverlay.backend.listeners;

import io.netty.channel.ChannelHandlerContext;
import lombok.SneakyThrows;
import me.oneqxz.partyoverlay.backend.manager.FriendManager;
import me.oneqxz.partyoverlay.backend.network.protocol.event.PacketSubscriber;
import me.oneqxz.partyoverlay.backend.network.protocol.io.Responder;
import me.oneqxz.partyoverlay.backend.network.protocol.packets.s2c.SFriendsSync;

/**
 * PartyOverlayClientBackend
 *
 * @author oneqxz
 * @since 22.04.2024
 */
public class FriendListener {

    @PacketSubscriber
    @SneakyThrows
    public void onFriendsSync(SFriendsSync packet, ChannelHandlerContext ctx, Responder responder) {
        FriendManager.getInstance().setOnlineFriends(packet.getOnlineFriends());
        FriendManager.getInstance().setOfflineFriends(packet.getOfflineFriends());
        FriendManager.getInstance().setFriendRequests(packet.getFriendRequests());
    }

}

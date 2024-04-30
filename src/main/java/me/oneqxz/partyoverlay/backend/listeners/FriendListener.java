package me.oneqxz.partyoverlay.backend.listeners;

import io.netty.channel.ChannelHandlerContext;
import lombok.SneakyThrows;
import me.oneqxz.partyoverlay.backend.PartyOverlayBackend;
import me.oneqxz.partyoverlay.backend.manager.FriendManager;
import me.oneqxz.partyoverlay.backend.network.protocol.event.PacketSubscriber;
import me.oneqxz.partyoverlay.backend.network.protocol.io.Responder;
import me.oneqxz.partyoverlay.backend.network.protocol.packets.s2c.SFriendJoin;
import me.oneqxz.partyoverlay.backend.network.protocol.packets.s2c.SFriendLeave;
import me.oneqxz.partyoverlay.backend.network.protocol.packets.s2c.SFriendRequestResult;
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


    @PacketSubscriber
    @SneakyThrows
    public void onFriendLeave(SFriendLeave packet, ChannelHandlerContext ctx, Responder responder) {
        PartyOverlayBackend.getInstance().getListener().onFriendLeave(packet.getId(), packet.getUsername());
    }


    @PacketSubscriber
    @SneakyThrows
    public void onFriendJoin(SFriendJoin packet, ChannelHandlerContext ctx, Responder responder) {
        PartyOverlayBackend.getInstance().getListener().onFriendJoin(packet.getId(), packet.getUsername(), packet.getMinecraftUsername());
    }

    @PacketSubscriber
    @SneakyThrows
    public void onFriendRequestResult(SFriendRequestResult packet, ChannelHandlerContext ctx, Responder responder) {
        PartyOverlayBackend.getInstance().getListener().onFriendRequestSend(packet.getResult());
    }

}

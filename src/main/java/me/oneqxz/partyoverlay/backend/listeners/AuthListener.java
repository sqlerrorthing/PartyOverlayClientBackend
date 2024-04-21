package me.oneqxz.partyoverlay.backend.listeners;

import io.netty.channel.ChannelHandlerContext;
import lombok.extern.log4j.Log4j2;
import me.oneqxz.partyoverlay.backend.PartyOverlayBackend;
import me.oneqxz.partyoverlay.backend.Session;
import me.oneqxz.partyoverlay.backend.network.protocol.event.PacketSubscriber;
import me.oneqxz.partyoverlay.backend.network.protocol.io.Responder;
import me.oneqxz.partyoverlay.backend.network.protocol.packets.c2s.CLogin;
import me.oneqxz.partyoverlay.backend.network.protocol.packets.s2c.SConnected;
import me.oneqxz.partyoverlay.backend.network.protocol.packets.s2c.SDisconnect;
import me.oneqxz.partyoverlay.backend.network.protocol.packets.s2c.SRequireLogin;
import me.oneqxz.partyoverlay.backend.sctructures.ServerData;

/**
 * PartyOverlayClientBackend
 *
 * @author oneqxz
 * @since 17.04.2024
 */
@Log4j2
public class AuthListener {

    @PacketSubscriber
    public void onRequireInfo(SDisconnect packet, ChannelHandlerContext ctx, Responder responder) {
        PartyOverlayBackend.getInstance().setSession(null);
        switch (packet.getReason()) {
            case INVALID_CREDITS:
                PartyOverlayBackend.getInstance().getListener().onInvalidCreditsDisconnect();
                break;

            case ALREADY_CONNECTED:
                PartyOverlayBackend.getInstance().getListener().onAlreadyConnectedDisconnect();
        }
    }

    @PacketSubscriber
    public void onRequireInfo(SRequireLogin packet, ChannelHandlerContext ctx, Responder responder) {
        log.info("Received SRequireLogin, session id: {}", packet.getConnectionUUID().toString());
        responder.respond(new CLogin(PartyOverlayBackend.getInstance().getMinecraftSessionProvider().getUsername(), PartyOverlayBackend.getInstance().getAuthCredits(),
                ServerData.builder()
                        .serverIP(PartyOverlayBackend.getInstance().getMinecraftProvider().getCurrentServer())
                        .build())
        );
    }

    @PacketSubscriber
    public void onConnect(SConnected packet, ChannelHandlerContext ctx, Responder responder) {
        log.info("Logged as {}", packet.getUsername());
        PartyOverlayBackend.getInstance().setSession(new Session(packet.getSessionUUID(), packet.getUsername()));
        PartyOverlayBackend.getInstance().getListener().onConnect(packet.getSessionUUID(), packet.getUsername());
    }

}

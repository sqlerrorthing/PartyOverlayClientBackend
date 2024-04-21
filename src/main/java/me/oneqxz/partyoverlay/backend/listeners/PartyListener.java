package me.oneqxz.partyoverlay.backend.listeners;

import io.netty.channel.ChannelHandlerContext;
import me.oneqxz.partyoverlay.backend.PartyOverlayBackend;
import me.oneqxz.partyoverlay.backend.manager.PartyManager;
import me.oneqxz.partyoverlay.backend.network.protocol.event.PacketSubscriber;
import me.oneqxz.partyoverlay.backend.network.protocol.io.Responder;
import me.oneqxz.partyoverlay.backend.network.protocol.packets.c2s.CPartySync;
import me.oneqxz.partyoverlay.backend.network.protocol.packets.s2c.SPartySync;
import me.oneqxz.partyoverlay.backend.providers.IPlayerProvider;
import me.oneqxz.partyoverlay.backend.sctructures.PartyMember;

import java.util.Arrays;
import java.util.List;

/**
 * PartyOverlayClientBackend
 *
 * @author oneqxz
 * @since 21.04.2024
 */
public class PartyListener {

    @PacketSubscriber
    public void onPartySync(SPartySync packet, ChannelHandlerContext ctx, Responder responder) {
        PartyManager.getInstance().setPartyName(packet.getName());
        PartyManager.getInstance().setPartyUUID(packet.getUuid());

        List<PartyMember> members = PartyManager.getInstance().getPartyMembers();
        members.clear();
        members.addAll(Arrays.asList(packet.getMembers()));

        IPlayerProvider provider = PartyOverlayBackend.getInstance().getPlayerProvider();
        if(provider.nonNull())
        {
            responder.respond(new CPartySync(
                    provider.getHealth(),
                    provider.getMaxHealth(),
                    provider.getYaw(),
                    provider.getPitch(),
                    provider.getX(),
                    provider.getY(),
                    provider.getZ()
            ));
        }
    }


}

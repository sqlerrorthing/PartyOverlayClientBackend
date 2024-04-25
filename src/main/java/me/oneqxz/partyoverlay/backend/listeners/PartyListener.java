package me.oneqxz.partyoverlay.backend.listeners;

import io.netty.channel.ChannelHandlerContext;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import me.oneqxz.partyoverlay.backend.PartyOverlayBackend;
import me.oneqxz.partyoverlay.backend.manager.PartyInvitesManager;
import me.oneqxz.partyoverlay.backend.manager.PartyManager;
import me.oneqxz.partyoverlay.backend.network.protocol.event.PacketSubscriber;
import me.oneqxz.partyoverlay.backend.network.protocol.io.Responder;
import me.oneqxz.partyoverlay.backend.network.protocol.packets.c2s.CPartySync;
import me.oneqxz.partyoverlay.backend.network.protocol.packets.s2c.SPartyInvitesSync;
import me.oneqxz.partyoverlay.backend.network.protocol.packets.s2c.SPartySync;
import me.oneqxz.partyoverlay.backend.providers.IPlayerProvider;
import me.oneqxz.partyoverlay.backend.sctructures.PartyMember;
import me.oneqxz.partyoverlay.backend.utils.LinkedSet;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * PartyOverlayClientBackend
 *
 * @author oneqxz
 * @since 21.04.2024
 */
@Log4j2
public class PartyListener {

    @PacketSubscriber
    @SneakyThrows
    public void onPartySync(SPartySync packet, ChannelHandlerContext ctx, Responder responder) {
        PartyManager.getInstance().setPartyName(packet.getName());
        PartyManager.getInstance().setPartyUUID(packet.getUuid());
        PartyManager.getInstance().setPartyInvited(new HashSet<>(Arrays.asList(packet.getPartyInvited())));

        Set<PartyMember> members = new LinkedSet<>();
        members.addAll(Arrays.asList(packet.getMembers()));
        PartyManager.getInstance().setPartyMembers(members);

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

    @PacketSubscriber
    @SneakyThrows
    public void onPartyInvitesSync(SPartyInvitesSync packet, ChannelHandlerContext ctx, Responder responder) {
        PartyInvitesManager.getInstance().setLastUpdatedPartyInvites(System.currentTimeMillis());
        PartyInvitesManager.getInstance().setInvites(packet.getInvites());
    }

}

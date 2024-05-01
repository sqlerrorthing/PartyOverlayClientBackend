package me.oneqxz.partyoverlay.backend.listeners;

import io.netty.channel.ChannelHandlerContext;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import me.oneqxz.partyoverlay.backend.PartyOverlayBackend;
import me.oneqxz.partyoverlay.backend.manager.PartyInvitesManager;
import me.oneqxz.partyoverlay.backend.manager.PartyManager;
import me.oneqxz.partyoverlay.backend.manager.PingManager;
import me.oneqxz.partyoverlay.backend.network.protocol.event.PacketSubscriber;
import me.oneqxz.partyoverlay.backend.network.protocol.io.Responder;
import me.oneqxz.partyoverlay.backend.network.protocol.packets.c2s.CPartySync;
import me.oneqxz.partyoverlay.backend.network.protocol.packets.s2c.*;
import me.oneqxz.partyoverlay.backend.providers.IPlayerProvider;
import me.oneqxz.partyoverlay.backend.sctructures.PartyMember;
import me.oneqxz.partyoverlay.backend.sctructures.Ping;
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
                    provider.getZ(),

                    provider.getHurtTime(),
                    provider.getDimension(),

                    provider.getMainHandItem(),
                    provider.getOffHandItem(),

                    provider.getHelmetItem(),
                    provider.getChestplateItem(),
                    provider.getLeggingsItem(),
                    provider.getBootsItem()
            ));
        }
    }


    @PacketSubscriber
    @SneakyThrows
    public void onPartyInvitesSync(SPartyInvitesSync packet, ChannelHandlerContext ctx, Responder responder) {
        PartyInvitesManager.getInstance().setLastUpdatedPartyInvites(System.currentTimeMillis());
        PartyInvitesManager.getInstance().setInvites(packet.getInvites());
    }


    @PacketSubscriber
    @SneakyThrows
    public void onPartyInviteResult(SPartyInviteResult packet, ChannelHandlerContext ctx, Responder responder) {
        PartyOverlayBackend.getInstance().getListener().onPartyInviteSend(packet.getResult());
    }


    @PacketSubscriber
    @SneakyThrows
    public void onPartyInviteReceived(SNewInvite packet, ChannelHandlerContext ctx, Responder responder) {
        PartyOverlayBackend.getInstance().getListener().onPartyInviteReceived(packet.getPartyUUID(), packet.getInviterUsername(), packet.getInviterMinecraftUsername());
    }


    @PacketSubscriber
    @SneakyThrows
    public void onPartyMemberJoin(SMemberPartyJoin packet, ChannelHandlerContext ctx, Responder responder) {
        PartyOverlayBackend.getInstance().getListener().onMemberPartyJoin(packet.getId(), packet.getUsername(), packet.getMinecraftUsername(), packet.getColor());
    }


    @PacketSubscriber
    @SneakyThrows
    public void onPartyMemberLeave(SMemberPartyLeave packet, ChannelHandlerContext ctx, Responder responder) {
        PartyOverlayBackend.getInstance().getListener().onMemberPartyLeave(packet.getId(), packet.getUsername(), packet.getMinecraftUsername());
    }


    @PacketSubscriber
    @SneakyThrows
    public void onPingAdd(SAddPing packet, ChannelHandlerContext ctx, Responder responder) {
        Ping ping = new Ping(
                packet.getPingUUID(),
                packet.getX(),
                packet.getY(),
                packet.getZ(),
                packet.getFrom()
        );

        PingManager.getInstance().add(ping);

        PartyOverlayBackend.getInstance().getListener().onPingAdd(ping);
    }

    @PacketSubscriber
    @SneakyThrows
    public void onPingRemove(SRemovePing packet, ChannelHandlerContext ctx, Responder responder) {
        PingManager.getInstance().remove(packet.getPingUUID());
        PartyOverlayBackend.getInstance().getListener().onPingRemove(packet.getPingUUID());
    }

}

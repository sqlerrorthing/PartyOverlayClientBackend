package me.oneqxz.partyoverlay.backend.listeners;

import io.netty.channel.ChannelHandlerContext;
import lombok.SneakyThrows;
import me.oneqxz.partyoverlay.backend.PartyOverlayBackend;
import me.oneqxz.partyoverlay.backend.manager.PartyManager;
import me.oneqxz.partyoverlay.backend.network.protocol.event.PacketSubscriber;
import me.oneqxz.partyoverlay.backend.network.protocol.io.Responder;
import me.oneqxz.partyoverlay.backend.network.protocol.packets.c2s.CPartySync;
import me.oneqxz.partyoverlay.backend.network.protocol.packets.s2c.SPartySync;
import me.oneqxz.partyoverlay.backend.providers.IPlayerProvider;
import me.oneqxz.partyoverlay.backend.sctructures.PartyMember;
import me.oneqxz.partyoverlay.backend.utils.LinkedSet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Set;

/**
 * PartyOverlayClientBackend
 *
 * @author oneqxz
 * @since 21.04.2024
 */
public class PartyListener {

    @PacketSubscriber
    @SneakyThrows
    public void onPartySync(SPartySync packet, ChannelHandlerContext ctx, Responder responder) {
        PartyManager.getInstance().setPartyName(packet.getName());
        PartyManager.getInstance().setPartyUUID(packet.getUuid());

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

    public byte[] readBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[1024];

        while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }

        buffer.flush();

        return buffer.toByteArray();
    }

}

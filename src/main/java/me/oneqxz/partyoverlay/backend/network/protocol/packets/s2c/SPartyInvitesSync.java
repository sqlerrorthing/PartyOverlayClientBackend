package me.oneqxz.partyoverlay.backend.network.protocol.packets.s2c;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.oneqxz.partyoverlay.backend.network.protocol.Packet;
import me.oneqxz.partyoverlay.backend.network.protocol.buffer.PacketBuffer;
import me.oneqxz.partyoverlay.backend.sctructures.PartyInvite;
import me.oneqxz.partyoverlay.backend.utils.LinkedSet;

import java.util.Set;
import java.util.UUID;

/**
 * PartyOverlayClientBackend
 *
 * @author oneqxz
 * @since 23.04.2024
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SPartyInvitesSync extends Packet {

    private Set<PartyInvite> invites;

    @Override
    public void read(PacketBuffer buffer) {
        int length = buffer.readInt();
        this.invites = new LinkedSet<>();

        for (int i = 0; i < length; i++) {
            String inviter = buffer.readUTF8();
            UUID partyUUID = buffer.readUUID();

            invites.add(new PartyInvite(inviter, partyUUID));
        }
    }

    @Override
    public void write(PacketBuffer buffer) {

    }
}

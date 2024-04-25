package me.oneqxz.partyoverlay.backend.network.protocol.packets.c2s;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.oneqxz.partyoverlay.backend.network.protocol.Packet;
import me.oneqxz.partyoverlay.backend.network.protocol.buffer.PacketBuffer;

import java.util.UUID;

/**
 * PartyOverlayClientBackend
 *
 * @author oneqxz
 * @since 24.04.2024
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CPartyInviteReject extends Packet {

    private UUID partyUUID;

    @Override
    public void read(PacketBuffer buffer) {
        this.partyUUID = buffer.readUUID();
    }

    @Override
    public void write(PacketBuffer buffer) {
        buffer.writeUUID(this.partyUUID);
    }
}

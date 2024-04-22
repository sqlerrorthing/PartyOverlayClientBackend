package me.oneqxz.partyoverlay.backend.network.protocol.packets.c2s;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.oneqxz.partyoverlay.backend.network.protocol.Packet;
import me.oneqxz.partyoverlay.backend.network.protocol.buffer.PacketBuffer;

/**
 * PartyOverlayClientBackend
 *
 * @author oneqxz
 * @since 22.04.2024
 */

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CSkinSync extends Packet {

    private byte[] skin;

    @Override
    public void read(PacketBuffer buffer) {
        this.skin = buffer.readByteArray();
    }

    @Override
    public void write(PacketBuffer buffer) {
        buffer.writeByteArray(skin);
    }
}

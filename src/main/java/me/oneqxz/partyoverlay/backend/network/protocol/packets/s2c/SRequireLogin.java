package me.oneqxz.partyoverlay.backend.network.protocol.packets.s2c;

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
 * @since 17.04.2024
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SRequireLogin extends Packet {

    private UUID connectionUUID;

    @Override
    public void read(PacketBuffer buffer) {
        this.connectionUUID = buffer.readUUID();
    }

    @Override
    public void write(PacketBuffer buffer) {
        buffer.writeUUID(this.connectionUUID);
    }
}


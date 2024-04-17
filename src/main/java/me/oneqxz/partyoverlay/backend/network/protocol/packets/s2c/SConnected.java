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
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SConnected extends Packet {

    private UUID sessionUUID;
    private String username;

    @Override
    public void read(PacketBuffer buffer) {
        sessionUUID = buffer.readUUID();
        username = buffer.readUTF8();
    }

    @Override
    public void write(PacketBuffer buffer) {
        buffer.writeUUID(sessionUUID);
        buffer.writeUTF8(username);
    }
}

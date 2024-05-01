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
 * @since 01.05.2024
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SAddPing extends Packet {

    private UUID pingUUID;
    private double x, y, z;
    private int from;

    @Override
    public void read(PacketBuffer buffer) {
        this.pingUUID = buffer.readUUID();
        this.x = buffer.readDouble();
        this.y = buffer.readDouble();
        this.z = buffer.readDouble();
        this.from = buffer.readInt();
    }

    @Override
    public void write(PacketBuffer buffer) {
        buffer.writeUUID(pingUUID);
        buffer.writeDouble(x);
        buffer.writeDouble(y);
        buffer.writeDouble(z);
        buffer.writeInt(from);
    }
}

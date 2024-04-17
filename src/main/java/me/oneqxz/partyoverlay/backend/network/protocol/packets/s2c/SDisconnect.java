package me.oneqxz.partyoverlay.backend.network.protocol.packets.s2c;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.oneqxz.partyoverlay.backend.network.protocol.Packet;
import me.oneqxz.partyoverlay.backend.network.protocol.buffer.PacketBuffer;

/**
 * PartyOverlayClientBackend
 *
 * @author oneqxz
 * @since 17.04.2024
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SDisconnect extends Packet {

    public enum Reason {
        INVALID_CREDITS,
        ALREADY_CONNECTED,
    }

    private Reason reason;

    @Override
    public void read(PacketBuffer buffer) {
        this.reason = buffer.readEnum(Reason.class);
    }

    @Override
    public void write(PacketBuffer buffer) {
        buffer.writeEnum(this.reason);
    }
}

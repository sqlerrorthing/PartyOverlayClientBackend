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
 * @since 26.04.2024
 */
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class SFriendLeave extends Packet {

    private int id;
    private String username;

    @Override
    public void read(PacketBuffer buffer) {
        this.id = buffer.readInt();
        this.username = buffer.readUTF8();
    }

    @Override
    public void write(PacketBuffer buffer) {
        buffer.writeInt(this.id);
        buffer.writeUTF8(this.username);
    }
}

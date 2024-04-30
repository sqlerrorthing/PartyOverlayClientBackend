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
 * @since 30.04.2024
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CRejectFriendRequest extends Packet {

    private String username;

    @Override
    public void read(PacketBuffer buffer) {
        this.username = buffer.readUTF8();
    }

    @Override
    public void write(PacketBuffer buffer) {
        buffer.writeUTF8(this.username);
    }
}

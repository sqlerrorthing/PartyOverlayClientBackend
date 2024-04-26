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
 * @since 27.04.2024
 */
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class SMemberPartyLeave extends Packet {

    private int id;
    private String username;
    private String minecraftUsername;

    @Override
    public void read(PacketBuffer buffer) {
        this.id = buffer.readInt();
        this.username = buffer.readUTF8();
        this.minecraftUsername = buffer.readUTF8();
    }

    @Override
    public void write(PacketBuffer buffer) {
        buffer.writeInt(this.id);
        buffer.writeUTF8(this.username);
        buffer.writeUTF8(this.minecraftUsername);
    }
}

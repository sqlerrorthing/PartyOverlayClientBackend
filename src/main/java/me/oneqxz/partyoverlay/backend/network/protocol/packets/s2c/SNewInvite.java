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
 * @since 27.04.2024
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SNewInvite extends Packet {

    private UUID partyUUID;
    private String inviterUsername;
    private String inviterMinecraftUsername;

    @Override
    public void read(PacketBuffer buffer) {
        this.partyUUID = buffer.readUUID();
        this.inviterUsername = buffer.readUTF8();
        this.inviterMinecraftUsername = buffer.readUTF8();
    }

    @Override
    public void write(PacketBuffer buffer) {
        buffer.writeUUID(this.partyUUID);
        buffer.writeUTF8(this.inviterUsername);
        buffer.writeUTF8(this.inviterMinecraftUsername);
    }
}

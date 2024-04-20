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
 * @since 21.04.2024
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CMinecraftUsernameChanged extends Packet {

    private String minecraftUsername;

    @Override
    public void read(PacketBuffer buffer) {
        this.minecraftUsername = buffer.readUTF8();
    }

    @Override
    public void write(PacketBuffer buffer) {
        buffer.writeUTF8(this.minecraftUsername);
    }
}

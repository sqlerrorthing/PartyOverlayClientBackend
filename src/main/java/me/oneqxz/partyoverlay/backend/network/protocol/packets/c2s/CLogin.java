package me.oneqxz.partyoverlay.backend.network.protocol.packets.c2s;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.oneqxz.partyoverlay.backend.network.protocol.Packet;
import me.oneqxz.partyoverlay.backend.network.protocol.buffer.PacketBuffer;
import me.oneqxz.partyoverlay.backend.sctructures.AuthCredits;
import me.oneqxz.partyoverlay.backend.sctructures.ServerData;

/**
 * PartyOverlayClientBackend
 *
 * @author oneqxz
 * @since 17.04.2024
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CLogin extends Packet {

    private String minecraftUsername;
    private AuthCredits credits;
    private ServerData serverData;

    @Override
    public void read(PacketBuffer buffer) {
        this.minecraftUsername = buffer.readUTF8();
        this.credits = new AuthCredits(
                buffer.readUTF8(),
                buffer.readUTF8()
        );

        String serverIP = buffer.readUTF8();
        this.serverData = ServerData.builder()
                .serverIP(serverIP.isEmpty() ? null : serverIP)
                .build();
    }

    @Override
    public void write(PacketBuffer buffer) {
        buffer.writeUTF8(minecraftUsername);

        buffer.writeUTF8(credits.getUsername());
        buffer.writeUTF8(credits.getPassword());
        buffer.writeUTF8(serverData == null || serverData.getServerIP() == null ? "[null]" : serverData.getServerIP());
    }
}

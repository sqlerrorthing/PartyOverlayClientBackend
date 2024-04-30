package me.oneqxz.partyoverlay.backend.network.protocol.packets.s2c;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.oneqxz.partyoverlay.backend.network.protocol.Packet;
import me.oneqxz.partyoverlay.backend.network.protocol.buffer.PacketBuffer;
import me.oneqxz.partyoverlay.backend.sctructures.FriendRequestResult;

/**
 * PartyOverlayClientBackend
 *
 * @author oneqxz
 * @since 27.04.2024
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SFriendRequestResult extends Packet {

    private FriendRequestResult result;

    @Override
    public void read(PacketBuffer buffer) {
        this.result = buffer.readEnum(FriendRequestResult.class);
    }

    @Override
    public void write(PacketBuffer buffer) {
        buffer.writeEnum(this.result);
    }
}

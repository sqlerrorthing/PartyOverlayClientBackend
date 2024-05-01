package me.oneqxz.partyoverlay.backend.network.protocol.packets.s2c;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.oneqxz.partyoverlay.backend.network.protocol.Packet;
import me.oneqxz.partyoverlay.backend.network.protocol.buffer.PacketBuffer;
import me.oneqxz.partyoverlay.backend.sctructures.Member;
import me.oneqxz.partyoverlay.backend.sctructures.PartyMember;
import me.oneqxz.partyoverlay.backend.sctructures.WrappedItemStack;

import java.awt.*;
import java.util.UUID;

/**
 * PartyOverlayClientBackend
 *
 * @author oneqxz
 * @since 21.04.2024
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SPartySync extends Packet {

    private UUID uuid;
    private String name;
    private PartyMember[] members;
    private String[] partyInvited;

    @Override
    public void read(PacketBuffer buffer) {
        this.uuid = buffer.readUUID();
        this.name = buffer.readUTF8();

        int length = buffer.readInt();

        this.members = new PartyMember[length];

        for (int i = 0; i < length; i++) {
            int memberId = buffer.readInt();
            String username = buffer.readUTF8();
            String minecraftUsername = buffer.readUTF8();
            boolean isOwner = buffer.readBoolean();
            Color playerColor = buffer.readColor();

            float health = buffer.readFloat();
            float maxHealth = buffer.readFloat();
            float yaw = buffer.readFloat();
            float pitch = buffer.readFloat();

            double posX = buffer.readDouble();
            double posY = buffer.readDouble();
            double posZ = buffer.readDouble();

            byte[] skin = buffer.readByteArray();
            int hurtTime = buffer.readInt();

            String dimension = buffer.readUTF8();
            String server = buffer.readUTF8();

            WrappedItemStack mainHandItem = WrappedItemStack.EMPTY().read(buffer);
            WrappedItemStack offHandItem = WrappedItemStack.EMPTY().read(buffer);

            WrappedItemStack helmetItem = WrappedItemStack.EMPTY().read(buffer);
            WrappedItemStack chestplateItem = WrappedItemStack.EMPTY().read(buffer);
            WrappedItemStack leggingsItem = WrappedItemStack.EMPTY().read(buffer);
            WrappedItemStack bootsItem = WrappedItemStack.EMPTY().read(buffer);

            Member member = new Member(memberId, username, minecraftUsername);
            this.members[i] = new PartyMember(member, isOwner, playerColor, health, maxHealth, yaw, pitch, posX, posY, posZ, skin,
                    hurtTime, dimension, server, mainHandItem, offHandItem, helmetItem, chestplateItem, leggingsItem, bootsItem);
        }

        length = buffer.readInt();
        this.partyInvited = new String[length];
        for(int i = 0; i < length; i++) {
            this.partyInvited[i] = buffer.readUTF8();
        }
    }

    @Override
    public void write(PacketBuffer buffer) {
        throw new IllegalStateException();
    }

}

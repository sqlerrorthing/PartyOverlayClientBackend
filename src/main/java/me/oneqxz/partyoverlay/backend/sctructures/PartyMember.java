package me.oneqxz.partyoverlay.backend.sctructures;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import me.oneqxz.partyoverlay.backend.PartyOverlayBackend;

import java.awt.*;

/**
 * PartyOverlayClientBackend
 *
 * @author oneqxz
 * @since 21.04.2024
 */
@Data
@AllArgsConstructor
@ToString
public class PartyMember {
    private Member member;
    private boolean isOwner;
    private Color playerColor;

    private float health, maxHealth, yaw, pitch;
    private double posX, posY, posZ;
    private byte[] skin;

    private int hurtTime;

    private WrappedItemStack mainHandItem;
    private WrappedItemStack offHandItem;

    private WrappedItemStack helmetItem;
    private WrappedItemStack chestplateItem;
    private WrappedItemStack leggingsItem;
    private WrappedItemStack bootsItem;

    public float getYawWrapped()
    {
        return wrapDegrees(this.yaw);
    }

    public float getPitchWrapped()
    {
        return wrapDegrees(this.pitch);
    }

    private float wrapDegrees(float value) {
        float f = value % 360.0F;

        if (f >= 180.0F) {
            f -= 360.0F;
        }

        if (f < -180.0F) {
            f += 360.0F;
        }

        return f;
    }

    public boolean isSelf()
    {
        return member.getId() == (PartyOverlayBackend.getInstance().getSession() != null ? PartyOverlayBackend.getInstance().getSession().getId() : Integer.MIN_VALUE);
    }

}

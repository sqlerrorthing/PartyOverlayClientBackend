package me.oneqxz.partyoverlay.backend.sctructures;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.awt.*;

/**
 * PartyOverlayClientBackend
 *
 * @author oneqxz
 * @since 21.04.2024
 */
@Data
@AllArgsConstructor
public class PartyMember {
    private Member member;
    private boolean isOwner;
    private Color playerColor;

    private float health, maxHealth, yaw, pitch;
    private double posX, posY, posZ;

}

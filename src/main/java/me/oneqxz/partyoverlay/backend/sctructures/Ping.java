package me.oneqxz.partyoverlay.backend.sctructures;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

/**
 * PartyOverlayClientBackend
 *
 * @author oneqxz
 * @since 01.05.2024
 */
@Getter
@AllArgsConstructor
public class Ping {

    private UUID uuid;
    private double x, y, z;

    private int from;

}

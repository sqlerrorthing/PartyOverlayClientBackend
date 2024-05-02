package me.oneqxz.partyoverlay.backend.sctructures;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * PartyOverlayClientBackend
 *
 * @author oneqxz
 * @since 01.05.2024
 */
@Getter
@RequiredArgsConstructor
public class Ping {

    @NonNull private UUID uuid;
    @NonNull private double x, y, z;

    @NonNull private int from;
    @Setter private boolean rendering3D;

}

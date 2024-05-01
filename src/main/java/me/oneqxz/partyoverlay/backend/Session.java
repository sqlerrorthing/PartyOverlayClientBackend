package me.oneqxz.partyoverlay.backend;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

/**
 * PartyOverlayClientBackend
 *
 * @author oneqxz
 * @since 18.04.2024
 */
@Getter
@AllArgsConstructor
public class Session {
    private UUID sessionUUID;
    private String username;
    private int id;
}

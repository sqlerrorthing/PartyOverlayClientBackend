package me.oneqxz.partyoverlay.backend.sctructures;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

/**
 * PartyOverlayClientBackend
 *
 * @author oneqxz
 * @since 23.04.2024
 */
@Getter
@AllArgsConstructor
public class PartyInvite {
    private String inviterUsername;
    private UUID partyUUID;
}

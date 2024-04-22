package me.oneqxz.partyoverlay.backend.sctructures.friend;

import lombok.Getter;

/**
 * PartyOverlayClientBackend
 *
 * @author oneqxz
 * @since 22.04.2024
 */
@Getter
public class OfflineFriend extends Friend {

    private final long lastSeen;

    public OfflineFriend(int id, String username, long lastSeen) {
        super(id, username);
        this.lastSeen = lastSeen;
    }
}

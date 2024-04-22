package me.oneqxz.partyoverlay.backend.sctructures.friend;

import lombok.Getter;
import me.oneqxz.partyoverlay.backend.sctructures.ServerData;

/**
 * PartyOverlayClientBackend
 *
 * @author oneqxz
 * @since 22.04.2024
 */
@Getter
public class OnlineFriend extends Friend {
    private final ServerData serverData;
    private final String minecraftUsername;
    private final byte[] skin;

    public OnlineFriend(int id, String username, byte[] skin, ServerData serverData, String minecraftUsername) {
        super(id, username);
        this.serverData = serverData;
        this.minecraftUsername = minecraftUsername;
        this.skin = skin;
    }

}

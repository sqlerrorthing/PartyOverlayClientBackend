package me.oneqxz.partyoverlay.backend.sctructures.friend;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * PartyOverlayClientBackend
 *
 * @author oneqxz
 * @since 22.04.2024
 */
@AllArgsConstructor
@Getter
public class FriendRequest {

    private int id;
    private String username;
    private RequestType requestType;

    public enum RequestType {
        OUTGOING,
        INCOMING;
    }

}

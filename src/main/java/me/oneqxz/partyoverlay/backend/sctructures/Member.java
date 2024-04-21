package me.oneqxz.partyoverlay.backend.sctructures;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * PartyOverlayClientBackend
 *
 * @author oneqxz
 * @since 21.04.2024
 */
@Data
@AllArgsConstructor
@ToString
public class Member {

    private int id;
    private String username;
    private String minecraftUsername;

}

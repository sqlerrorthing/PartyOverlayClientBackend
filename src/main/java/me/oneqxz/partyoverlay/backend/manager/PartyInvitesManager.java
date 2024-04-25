package me.oneqxz.partyoverlay.backend.manager;

import lombok.Getter;
import lombok.Setter;
import me.oneqxz.partyoverlay.backend.sctructures.PartyInvite;
import me.oneqxz.partyoverlay.backend.utils.LinkedSet;

import java.util.Set;

/**
 * PartyOverlayClientBackend
 *
 * @author oneqxz
 * @since 24.04.2024
 */
@Getter
@Setter
public class PartyInvitesManager {

    private static PartyInvitesManager INSTANCE;
    private Set<PartyInvite> invites = new LinkedSet<>();
    private long lastUpdatedPartyInvites;

    private PartyInvitesManager()
    {

    }

    public Set<PartyInvite> getInvites() {
        if(System.currentTimeMillis() - lastUpdatedPartyInvites > 3000)
            this.invites.clear();

        return invites;
    }

    public static PartyInvitesManager getInstance()
    {
        return INSTANCE == null ? INSTANCE = new PartyInvitesManager() : INSTANCE;
    }

}

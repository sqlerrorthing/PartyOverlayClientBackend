package me.oneqxz.partyoverlay.backend.manager;

import lombok.Getter;
import lombok.Setter;
import me.oneqxz.partyoverlay.backend.PartyOverlayBackend;
import me.oneqxz.partyoverlay.backend.network.protocol.packets.c2s.CPartyCreate;
import me.oneqxz.partyoverlay.backend.sctructures.PartyMember;
import me.oneqxz.partyoverlay.backend.utils.ConnectionUtils;
import me.oneqxz.partyoverlay.backend.utils.LinkedSet;

import java.util.Set;
import java.util.UUID;

/**
 * PartyOverlayClientBackend
 *
 * @author oneqxz
 * @since 21.04.2024
 */
@Setter
@Getter
public class PartyManager {

    private static PartyManager INSTANCE;
    private UUID partyUUID;
    private String partyName;
    private Set<PartyMember> partyMembers = new LinkedSet<>();

    public boolean isOnParty()
    {
        if(!PartyOverlayBackend.getInstance().isConnected() || partyUUID == null || partyName == null)
            return false;

        return !partyMembers.isEmpty();
    }

    public void reset()
    {
        this.partyUUID = null;
        this.partyName = null;
        this.partyMembers.clear();
    }

    public void createParty()
    {
        if(PartyOverlayBackend.getInstance().getSession() == null)
            return;

        ConnectionUtils.sendPacketIfConnected(new CPartyCreate(
                PartyOverlayBackend.getInstance().getSession().getUsername() + "'s party"
        ));
    }

    public static PartyManager getInstance() {
        return INSTANCE == null ? INSTANCE = new PartyManager() : INSTANCE;
    }

}

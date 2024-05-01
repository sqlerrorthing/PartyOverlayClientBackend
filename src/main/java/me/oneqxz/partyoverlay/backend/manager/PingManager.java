package me.oneqxz.partyoverlay.backend.manager;

import lombok.Getter;
import me.oneqxz.partyoverlay.backend.sctructures.Ping;
import me.oneqxz.partyoverlay.backend.utils.LinkedSet;

import java.util.Set;
import java.util.UUID;

/**
 * PartyOverlayClientBackend
 *
 * @author oneqxz
 * @since 01.05.2024
 */
@Getter
public class PingManager {

    private static PingManager INSTANCE;
    private final Set<Ping> pings = new LinkedSet<>();

    private PingManager() {}

    public void add(Ping ping) {
        this.pings.add(ping);
    }

    public void remove(Ping ping) {
        this.pings.remove(ping);
    }

    public void remove(UUID pingUUID)
    {
        this.pings.removeIf(p -> p.getUuid().equals(pingUUID));
    }

    public void clear()
    {
        pings.clear();
    }

    public static PingManager getInstance()
    {
        return INSTANCE == null ? INSTANCE = new PingManager() : INSTANCE;
    }

}

package me.oneqxz.partyoverlay.backend.manager;

import lombok.Getter;
import lombok.Setter;
import me.oneqxz.partyoverlay.backend.network.protocol.packets.s2c.SFriendsSync;
import me.oneqxz.partyoverlay.backend.sctructures.friend.OfflineFriend;
import me.oneqxz.partyoverlay.backend.sctructures.friend.OnlineFriend;
import me.oneqxz.partyoverlay.backend.utils.LinkedSet;

import java.util.Set;

/**
 * PartyOverlayClientBackend
 *
 * @author oneqxz
 * @since 22.04.2024
 */
@Getter
@Setter
public class FriendManager {

    private static FriendManager INSTANCE;

    private Set<OnlineFriend> onlineFriends = new LinkedSet<>();
    private Set<OfflineFriend> offlineFriends = new LinkedSet<>();
    private Set<SFriendsSync.FriendRequest> friendRequests = new LinkedSet<>();

    private FriendManager()
    {

    }

    public void reset()
    {
        this.onlineFriends.clear();
        this.offlineFriends.clear();
        this.friendRequests.clear();
    }

    public static FriendManager getInstance() {
        return INSTANCE == null ? INSTANCE = new FriendManager() : INSTANCE;
    }

}

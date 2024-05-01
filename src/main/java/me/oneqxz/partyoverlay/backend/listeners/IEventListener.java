package me.oneqxz.partyoverlay.backend.listeners;

import me.oneqxz.partyoverlay.backend.sctructures.FriendRequestResult;
import me.oneqxz.partyoverlay.backend.sctructures.InviteResult;
import me.oneqxz.partyoverlay.backend.sctructures.Ping;

import java.awt.*;
import java.util.UUID;

/**
 * PartyOverlayClientBackend
 *
 * @author oneqxz
 * @since 17.04.2024
 */
public interface IEventListener {

    void onInvalidCreditsDisconnect();
    void onAlreadyConnectedDisconnect();
    void onServerErrorDisconnect();

    void onConnect(UUID sessionUUID, String username);

    /**
     * is called when a friend is logged in.
     * <p>can be used in:</p>
     * <ul>
     *     <li>notifications</li>
     * </ul>
     *
     * @param id his id
     * @param username his username is PartyOverlay.
     * @param minecraftName his username in Minecraft.
     */
    void onFriendJoin(int id, String username, String minecraftName);

    /**
     * is called when a friend is logs out.
     * <p>can be used in:</p>
     * <ul>
     *     <li>notifications</li>
     * </ul>
     * @param id his id
     * @param username his username in PartyOverlay;
     */
    void onFriendLeave(int id, String username);

    /**
     * is invoked from all party members when a user has joined into the party
     * <p>can be used in:</p>
     * <ul>
     *     <li>notifications</li>
     * </ul>
     * @param id his id
     * @param username his username is PartyOverlay.
     * @param minecraftName his username in Minecraft.
     */
    void onMemberPartyJoin(int id, String username, String minecraftName, Color color);

    /**
     * is invoked on all party members when the user leaves the party
     * <p>can be used in:</p>
     * <ul>
     *     <li>notifications</li>
     * </ul>
     * @param id his id
     * @param username his username is PartyOverlay.
     * @param minecraftName his username in Minecraft.
     */
    void onMemberPartyLeave(int id, String username, String minecraftName);

    /**
     * is summoned when going out and partying (or kicked out).
     * <p>can be used in:</p>
     * <ul>
     *     <li>notifications</li>
     * </ul>
     * @param partyUUID unique party identifier
     */
    void onPartyLeaved(UUID partyUUID);

    /**
     * is invoked when a party invitation is sent to a friend.
     * <p>can be used in:</p>
     * <ul>
     *     <li>notifications</li>
     * </ul>
     * @param result invitation {@link InviteResult status}
     */
    void onPartyInviteSend(InviteResult result);

    /**
     * is invoked when a new invite to the party is received
     * @param partyUUID unique party identifier
     * @param inviterUsername username in PartyOverlay who invited
     * @param inviterMinecraftName username in Minecraft.
     */
    void onPartyInviteReceived(UUID partyUUID, String inviterUsername, String inviterMinecraftName);

    /**
     * is invoked when a user sends a friend request to another user
     *
     * @param status request status
     */
    void onFriendRequestSend(FriendRequestResult status);

    void onPingAdd(Ping ping);
    void onPingRemove(UUID pingUUID);
}

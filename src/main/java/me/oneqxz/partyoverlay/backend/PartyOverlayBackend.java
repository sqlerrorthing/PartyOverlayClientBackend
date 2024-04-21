package me.oneqxz.partyoverlay.backend;

import io.netty.channel.ChannelFuture;
import lombok.Getter;
import lombok.Setter;
import me.oneqxz.partyoverlay.backend.listeners.IEventListener;
import me.oneqxz.partyoverlay.backend.listeners.InternalEvents;
import me.oneqxz.partyoverlay.backend.network.ServerConnection;
import me.oneqxz.partyoverlay.backend.providers.IMinecraftProvider;
import me.oneqxz.partyoverlay.backend.providers.IMinecraftSessionProvider;
import me.oneqxz.partyoverlay.backend.providers.IPlayerProvider;
import me.oneqxz.partyoverlay.backend.sctructures.AuthCredits;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * PartyOverlayClientBackend
 *
 * @author oneqxz
 * @since 17.04.2024
 */
@Getter
public final class PartyOverlayBackend {

    private static PartyOverlayBackend INSTANCE;
    @Setter
    @Nullable private AuthCredits authCredits = new AuthCredits("", "");

    @Getter @Setter private Session session;

    private IMinecraftProvider minecraftProvider;
    private IMinecraftSessionProvider minecraftSessionProvider;
    private IPlayerProvider playerProvider;

    private IEventListener listener;
    private InternalEvents internalEvents;
    private boolean init;

    public void init(@NotNull IMinecraftProvider minecraft,
                     @NotNull IMinecraftSessionProvider session,
                     @NotNull IPlayerProvider playerProvider,

                     @NotNull IEventListener listener)
    {
        this.minecraftProvider = minecraft;
        this.minecraftSessionProvider = session;
        this.playerProvider = playerProvider;

        this.listener = listener;
        this.internalEvents = new InternalEvents();

        ServerConnection.getInstance().connect();
        this.init = true;
    }

    public boolean isConnected()
    {
        ChannelFuture connection = ServerConnection.getInstance().getConnection();
        return connection != null && connection.channel().isOpen();
    }

    public static PartyOverlayBackend getInstance()
    {
        return INSTANCE == null ? INSTANCE = new PartyOverlayBackend() : INSTANCE;
    }

}

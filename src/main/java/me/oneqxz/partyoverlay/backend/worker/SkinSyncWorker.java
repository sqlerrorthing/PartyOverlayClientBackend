package me.oneqxz.partyoverlay.backend.worker;

import me.oneqxz.partyoverlay.backend.PartyOverlayBackend;
import me.oneqxz.partyoverlay.backend.network.ServerConnection;
import me.oneqxz.partyoverlay.backend.network.protocol.packets.c2s.CSkinSync;
import me.oneqxz.partyoverlay.backend.utils.InputStreamUtils;

/**
 * PartyOverlayClientBackend
 *
 * @author oneqxz
 * @since 22.04.2024
 */
public class SkinSyncWorker implements Runnable {

    private byte[] oldSkin = new byte[0];

    public void run()
    {
        if(!PartyOverlayBackend.getInstance().isConnected())
            return;

        byte[] newSkin = InputStreamUtils.readInputStream(PartyOverlayBackend.getInstance().getPlayerProvider().getSkinInputStream());
        if(newSkin.length == 0)
            return;

        if(oldSkin != newSkin)
            doSend(newSkin);

        oldSkin = newSkin;

    }

    private void doSend(byte[] skin)
    {
        ServerConnection.getInstance().getConnection().channel().writeAndFlush(new CSkinSync(skin));
    }

}

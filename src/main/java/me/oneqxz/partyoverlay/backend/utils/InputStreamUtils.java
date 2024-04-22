package me.oneqxz.partyoverlay.backend.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * PartyOverlayClientBackend
 *
 * @author oneqxz
 * @since 21.04.2024
 */
public class InputStreamUtils {

    public static byte[] readInputStream(InputStream in) {
        try
        {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            return outputStream.toByteArray();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return new byte[0];
        }
    }
}

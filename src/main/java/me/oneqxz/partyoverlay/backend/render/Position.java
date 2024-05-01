package me.oneqxz.partyoverlay.backend.render;

import me.oneqxz.partyoverlay.backend.PartyOverlayBackend;
import org.jetbrains.annotations.Range;

/**
 * PartyOverlayClientBackend
 *
 * @author oneqxz
 * @since 30.04.2024
 */
public class Position {
    @Range(from = 0, to = 1)
    private double x, y;
    public int prevAbsX, prevAbsY;

    private Position(double x, double y) {
        this.setRelative(x, y);
    }

    public static Position fromRelative(double x, double y) {
        return new Position(x, y);
    }

    public static Position fromAbsolute(int x, int y)
    {
        Position pos =  new Position(x, y);
        pos.setAbsolute(x, y);
        return pos;
    }

    public int getAbsoluteX()
    {
        return (int) (x * PartyOverlayBackend.getInstance().getDisplayProvider().getWidth());
    }

    public int getAbsoluteY()
    {
        return (int) (y * PartyOverlayBackend.getInstance().getDisplayProvider().getHeight());
    }

    public double getRelativeX()
    {
        return x;
    }

    public double getRelativeY()
    {
        return y;
    }

    public void setRelative(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public void setAbsolute(int x, int y)
    {
        this.x = (double) x / PartyOverlayBackend.getInstance().getDisplayProvider().getWidth();
        this.y = (double) y / PartyOverlayBackend.getInstance().getDisplayProvider().getHeight();
    }
}

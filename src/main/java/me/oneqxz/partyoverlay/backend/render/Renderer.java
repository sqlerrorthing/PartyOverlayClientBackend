package me.oneqxz.partyoverlay.backend.render;

import lombok.Getter;

/**
 * PartyOverlayClientBackend
 *
 * @author oneqxz
 * @since 30.04.2024
 */
@Getter
public abstract class Renderer<T, M> implements IRenderer<T> {

    private final Resolution res;
    private final Position pos;
    protected final M mc;
    public boolean dragging;

    public Renderer(Position pos, M mc)
    {
        this.pos = pos;
        this.mc = mc;
        this.res = new Resolution(0, 0);
    }

    public void update()
    {
        this.res.width = this.getWidth();
        this.res.height = this.getHeight();
    }

    public void render(T stack)
    {
        this.update();
        this.render(stack, pos, res);
    }

    public void renderDummy(T stack)
    {
        this.update();
        this.renderDummy(stack, pos, res);
    }
}

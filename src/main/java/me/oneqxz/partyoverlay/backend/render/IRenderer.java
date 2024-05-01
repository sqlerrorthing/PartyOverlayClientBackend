package me.oneqxz.partyoverlay.backend.render;

/**
 * PartyOverlayClientBackend
 *
 * @author oneqxz
 * @since 30.04.2024
 */
public interface IRenderer<T> {

    int getWidth();
    int getHeight();

    void render(T stack, Position pos, Resolution res);
    default void renderDummy(T stack, Position pos, Resolution res) {
        render(stack, pos, res);
    }
}

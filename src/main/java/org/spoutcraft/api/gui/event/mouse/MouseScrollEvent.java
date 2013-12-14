package org.spoutcraft.api.gui.event.mouse;

import org.spoutcraft.api.gui.Component;

public class MouseScrollEvent extends MouseEvent{

    private final int amnt;

    public MouseScrollEvent(Component source, int btn, int x, int y, int amnt) {
        super(source, btn, x, y, MouseEvent.MOUSE_SCROLL);
        this.amnt = amnt;
    }

    @Override
    public int getScrollAmnt() {
        return this.amnt;
    }

}

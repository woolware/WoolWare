package wool.ware.client.event.impl.input;

import wool.ware.client.event.Event;


 
public class KeyInputEvent extends Event {
    private int key;

    public KeyInputEvent(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }
}

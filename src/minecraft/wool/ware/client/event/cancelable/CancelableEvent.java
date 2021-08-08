package wool.ware.client.event.cancelable;

import wool.ware.client.event.Event;


public class CancelableEvent extends Event {
    private boolean canceled;

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }
}

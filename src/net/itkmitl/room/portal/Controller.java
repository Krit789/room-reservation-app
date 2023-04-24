package net.itkmitl.room.portal;

public abstract class Controller {
    protected abstract void start();

    protected abstract void initialize();

    protected abstract void initializeListener();

    protected void changeFrame(View current, Portal destination) {
        current.dispose();
        destination.run();
    }
}

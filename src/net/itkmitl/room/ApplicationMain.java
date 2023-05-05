package net.itkmitl.room;

import net.itkmitl.room.libs.store.AppStore;

public class ApplicationMain {
    public static void main(String[] args) {
        AppStore appState = AppStore.getAppStore();
        appState.dispatch("number", 1);

        System.out.println("Before 1: " + appState.select("number"));

        AppStore appState2 = AppStore.getAppStore();
        appState2.dispatch("number", 2);

        System.out.println("After 1: " + appState.select("number"));
        System.out.println("After 2: " + appState2.select("number"));
    }
}

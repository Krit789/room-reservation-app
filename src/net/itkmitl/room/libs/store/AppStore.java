package net.itkmitl.room.libs.store;

import java.util.HashMap;

public class AppStore {
    private static AppStore instance;
    private static HashMap<String, Object> store;

    public static AppStore getAppStore() {
        if (AppStore.instance == null) {
            AppStore.instance = AppStore.create();
        }
        return AppStore.instance;
    }

    private static AppStore create() {
        System.out.println("Created!");

        AppStore.store = new HashMap<>();
        return new AppStore();
    }

    public void dispatch(String key, Object value) {
        AppStore.store.put(key, value);
    }

    public <T> T select(String key) {
        return (T) AppStore.store.get(key);
    }
}

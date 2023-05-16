package net.itkmitl.room.libs.store;

import java.util.HashMap;

import net.itkmitl.room.libs.peeranat.simplevalue.FewSimpleValue;

public class AppStore {
    private static AppStore instance;
    private static HashMap<String, Object> store;

    public static AppStore getAppStore() {
        if (AppStore.instance == null) {
            AppStore.store = new HashMap<>();
            AppStore.instance = new AppStore();
        }
        return AppStore.instance;
    }

    public void dispatch(String key, Object value) {
        AppStore.store.put(key, value);
    }

    public Object select(String key) {
    	return selectRaw(key).getRaw();
    }

	public FewSimpleValue selectRaw(String key) {
        return new FewSimpleValue(AppStore.store.get(key));
    }
}

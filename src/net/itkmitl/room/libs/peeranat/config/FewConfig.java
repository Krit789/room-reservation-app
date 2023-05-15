package net.itkmitl.room.libs.peeranat.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.itkmitl.room.libs.peeranat.simplevalue.FewSimpleValue;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

public class FewConfig {

	private File file;
	private Yaml yaml;
	private HashMap<String, Object> data;

	public FewConfig(File file) {
		this.file = file;
		try {
			InputStream inputStream = new FileInputStream(file);

			DumperOptions options = new DumperOptions();
			options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);

			this.yaml = new Yaml(options);
			this.data = this.yaml.load(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// If could load default config
		if (this.data == null) {
			this.data = new HashMap<>();
		}
	}

	public FewSimpleValue getValue(String key) {
		if (data.get(key) == null) {
			return null;
		}
		return new FewSimpleValue(data.get(key));
	}

	public void dumpInfo() {
		for (Map.Entry<String, Object> values : data.entrySet()) {
			System.out.println(values.getKey() + ": " + values.getValue());
		}
	}

	public FewConfig reloadConfig() {
		try {
			this.data = this.yaml.load(new FileInputStream(this.file));
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return this;
	}

	public FewConfig set(String key, Object value) {
		this.data.put(key, value);
		return this;
	}

	public FewConfig saveConfig() {
		try {
			FileWriter writer = new FileWriter(this.file);
			this.yaml.dump(this.data, writer);
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		reloadConfig();
		return this;
	}

	public File getFile() {
		return file;
	}
}

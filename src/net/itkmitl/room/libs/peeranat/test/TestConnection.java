package net.itkmitl.room.libs.peeranat.test;

import java.io.File;

import net.itkmitl.room.libs.peeranat.config.FewConfig;

public class TestConnection {

	public static void main(String[] args) {
		FewConfig config = new FewConfig(new File("config.yml"));
		config.dumpInfo();
	}
	
}

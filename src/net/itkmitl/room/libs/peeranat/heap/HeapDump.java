package net.itkmitl.room.libs.peeranat.heap;

import java.io.IOException;
import java.nio.file.Paths;

public class HeapDump {


	public static java.nio.file.Path dumpHeap(String name) throws IOException {
		return dumpHeap(name, Paths.get("."));
	}

    public static java.nio.file.Path dumpHeap(String name, java.nio.file.Path dir) throws IOException {

    	try {
			java.nio.file.Files.createDirectories(dir);

	        javax.management.MBeanServer server = java.lang.management.ManagementFactory.getPlatformMBeanServer();
	        java.nio.file.Path file;

	        try {
	            Class<?> clazz = Class.forName("openj9.lang.management.OpenJ9DiagnosticsMXBean");
	            Object openj9Mbean = java.lang.management.ManagementFactory.newPlatformMXBeanProxy(server, "openj9.lang.management:type=OpenJ9Diagnostics", clazz);
	            java.lang.reflect.Method m = clazz.getMethod("triggerDumpToFile", String.class, String.class);
	            file = dir.resolve(name + ".phd");
	            m.invoke(openj9Mbean, "heap", file.toString());
	        } catch (ClassNotFoundException e) {
	        	Class<?> clazz = Class.forName("com.sun.management.HotSpotDiagnosticMXBean");
	            Object hotspotMBean = java.lang.management.ManagementFactory.newPlatformMXBeanProxy(server, "com.sun.management:type=HotSpotDiagnostic", clazz);
	            java.lang.reflect.Method m = clazz.getMethod("dumpHeap", String.class, boolean.class);
	            file = dir.resolve(name + ".hprof");
	            m.invoke(hotspotMBean, file.toString(), true);
	        }

	        return file;
	    } catch (Throwable t) {
	        System.err.println("Could not write heap");
	        return null;
	    }
    }

}

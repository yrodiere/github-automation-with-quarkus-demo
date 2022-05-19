package org.acme.demo;

import java.util.Map;
import java.util.TreeMap;

public class RepositoryConfig {
	public Map<String, ComponentConfig> components = new TreeMap<>();

	public static class ComponentConfig {
		public String owner;
	}
}

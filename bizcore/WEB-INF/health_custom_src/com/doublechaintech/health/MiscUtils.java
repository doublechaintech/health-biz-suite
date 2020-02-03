package com.doublechaintech.health;

import java.util.List;

import com.terapico.caf.viewpage.SerializeScope;

public class MiscUtils extends HealthBaseUtils {

	public static SerializeScope buildEntityScope(String... propertyNames) {
		if (propertyNames == null) {
			return null;
		}
		SerializeScope scope = SerializeScope.INCLUDE();
		for (String propertyname : propertyNames) {
			scope.field(propertyname);
		}
		return scope;
	}
	
	public static String getEnumValueByKey(List<KeyValuePair> pairs, String key) {
		if (pairs == null || key == null) {
			return null;
		}
		return (String) pairs
				.stream()
					.filter(p -> key.equals(p.getKey()))
					.findFirst()
					.map(KeyValuePair::getValue)
					.orElse(null);
	}
}

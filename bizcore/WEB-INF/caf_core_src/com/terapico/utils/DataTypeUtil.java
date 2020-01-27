package com.terapico.utils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DataTypeUtil {
	public static boolean isMonoType(Object value) {
		if (value == null) {
			return false;
		}
		if  (value instanceof Collection) {
			return false;
		}
		if (value instanceof Map) {
			return false;
		}
		if (value.getClass().isArray()) {
			return false;
		}
		return true;
	}
	// int
	public static Integer getInt(Object value) {
		return getInt(value, null);
	}
	public static Integer getInt(Object value, Integer defaultValue) {
		try {
			if (value == null) {
				return defaultValue;
			}
			if (value instanceof Number) {
				return ((Number) value).intValue();
			}
			if (value instanceof String) {
				return Integer.parseInt((String) value);
			}
			return defaultValue;
		}catch (Throwable t) {
			return defaultValue;
		}
	}
	// long
	public static Long getLong(Object value) {
		return getLong(value, null);
	}
	public static Long getLong(Object value, Long defaultValue) {
		try {
			if (value == null) {
				return defaultValue;
			}
			if (value instanceof Number) {
				return ((Number) value).longValue();
			}
			if (value instanceof String) {
				return Long.parseLong((String) value);
			}
			return defaultValue;
		}catch (Throwable t) {
			return defaultValue;
		}
	}
	// boolean
	public static Boolean getBoolean(Object value) {
		return getBoolean(value, null);
	}

	protected static final Set<String> TRUE_WORDS = new HashSet<>(
			Arrays.asList("yes", "true", "ok", "o.k.", "correct", "enable", "enabled", "agree", "right", "agreed"));

	public static Boolean getBoolean(Object value, Boolean defaultValue) {
		try {
			if (value == null) {
				return defaultValue;
			}
			if (value instanceof Boolean) {
				return (Boolean) value;
			}
			if (value instanceof String) {
				String lowStr = ((String)value).toLowerCase().trim();
				if (TRUE_WORDS.contains(lowStr)) {
					return true;
				}
				return Boolean.parseBoolean(lowStr);
			}
			if (value instanceof Number) {
				return ((Number) value).intValue() != 0;
			}
			return defaultValue;
		}catch (Throwable t) {
			return defaultValue;
		}
	}
	// BigDecimal
	public static BigDecimal getBigDecimal(Object value) {
		return getBigDecimal(value, null);
	}
	public static BigDecimal getBigDecimal(Object value, String defaultValue) {
		try {
			if (value == null) {
				return string2BigDecimal(defaultValue);
			}
			if (value instanceof BigDecimal) {
				return (BigDecimal) value;
			}
			if (value instanceof String) {
				if (TextUtil.isBlank((String) value)) {
					return string2BigDecimal(defaultValue);
				}
				return new BigDecimal((String)value);
			}
			if (value instanceof Number) {
				return new BigDecimal(String.valueOf(value));
			}
			return string2BigDecimal(defaultValue);
		}catch (Throwable t) {
			return string2BigDecimal(defaultValue);
		}
	}
	private static BigDecimal string2BigDecimal(String valueStr) {
		if (TextUtil.isBlank(valueStr)) {
			return null;
		}
		return new BigDecimal(valueStr.trim());
	}
	// Date
	public static Date getDate(Object value) {
		return getDate(value, null);
	}
	public static Date getDate(Object value, Date defaultValue) {
		try {
			if (value == null) {
				return defaultValue;
			}
			if (value instanceof String) {
				return DateTimeUtil.parseInputDateTime((String)value);
			}
			if (value instanceof Number) {
				return new Date(((Number) value).longValue());
			}
			if (value instanceof Date) {
				return (Date) value;
			}
			return defaultValue;
		}catch (Throwable t) {
			return defaultValue;
		}
	}
}

package kr.ac.jj.shared.infrastructure.framework.core.support.collection;

import java.math.BigDecimal;
import java.util.Date;

public class ValueConverter {

    public static Boolean getBoolean(Object value) {
        return getBoolean(value, false);
    }

    public static Boolean getBoolean(Object value, boolean useNull) {
        if (value == null) {
            if (useNull) {
                return null;
            }
            return false;
        }

        Class<?> cls = value.getClass();

        if (cls.isInstance(Boolean.TRUE)) {
            return ((Boolean) value).booleanValue();
        }

        if (cls.isInstance(new String())) {
            return "true".equalsIgnoreCase(value.toString());
        }

        if (cls == Integer.class || cls == BigDecimal.class) {
            return "1".equals(value.toString());
        }

        return Boolean.valueOf(value.toString()).booleanValue();
    }

    public static Byte getByte(Object value) {
        return getByte(value, false);
    }

    public static Byte getByte(Object value, boolean useNull) {
        if (value == null) {
            if (useNull) {
                return null;
            }
            return 0;
        }

        Class<?> cls = value.getClass();

        if (cls == Byte.class) {
            return ((Byte) value).byteValue();
        }

        if (cls == String.class && ((String) value).getBytes().length > 0) {
            return ((String) value).getBytes()[0];
        }

        if (value.toString().length() > 0) {
            return value.toString().getBytes()[0];
        }

        return 0;
    }

    public static Character getCharacter(Object value) {
        return getCharacter(value, false);
    }

    public static Character getCharacter(Object value, boolean useNull) {
        if (value == null) {
            if (useNull) {
                return null;
            }
            return 0;
        }

        Class<?> cls = value.getClass();

        if (cls == Character.class) {
            return ((Character) value).charValue();
        }

        if (cls == String.class && ((String) value).length() > 0) {
            return ((String) value).charAt(0);
        }

        if (value.toString().length() > 0) {
            return value.toString().charAt(0);
        }

        return 0;
    }

    public static Short getShort(Object value) {
        return getShort(value, false);
    }

    public static Short getShort(Object value, boolean useNull) {
        if (value == null) {
            if (useNull) {
                return null;
            }
            return 0;
        }

        Class<?> cls = value.getClass();

        if (cls == Short.class) {
            return ((Short) value).shortValue();
        }

        if (cls == Integer.class) {
            return ((Integer) value).shortValue();
        }

        if (cls == Long.class) {
            return ((Long) value).shortValue();
        }

        if (cls == Float.class) {
            return ((Float) value).shortValue();
        }

        if (cls == Double.class) {
            return ((Double) value).shortValue();
        }

        return Short.parseShort(value.toString());
    }

    public static Integer getInteger(Object value) {
        return getInteger(value, false);
    }

    public static Integer getInteger(Object value, boolean useNull) {
        if (value == null) {
            if (useNull) {
                return null;
            }
            return 0;
        }

        Class<?> cls = value.getClass();

        if (cls == Short.class) {
            return ((Short) value).intValue();
        }

        if (cls == Integer.class) {
            return ((Integer) value).intValue();
        }

        if (cls == Long.class) {
            return ((Long) value).intValue();
        }

        if (cls == Float.class) {
            return ((Float) value).intValue();
        }

        if (cls == Double.class) {
            return ((Double) value).intValue();
        }

        return Integer.parseInt(value.toString());
    }

    public static Long getLong(Object value) {
        return getLong(value, false);
    }

    public static Long getLong(Object value, boolean useNull) {
        if (value == null) {
            if (useNull) {
                return null;
            }
            return 0L;
        }

        Class<?> cls = value.getClass();

        if (cls == Short.class) {
            return ((Short) value).longValue();
        }

        if (cls == Integer.class) {
            return ((Integer) value).longValue();
        }

        if (cls == Long.class) {
            return ((Long) value).longValue();
        }

        if (cls == Float.class) {
            return ((Float) value).longValue();
        }

        if (cls == Double.class) {
            return ((Double) value).longValue();
        }

        return Long.parseLong(value.toString());
    }

    public static Float getFloat(Object value) {
        return getFloat(value, false);
    }

    public static Float getFloat(Object value, boolean useNull) {
        if (value == null) {
            if (useNull) {
                return null;
            }
            return 0F;
        }

        Class<?> cls = value.getClass();

        if (cls == Short.class) {
            return ((Short) value).floatValue();
        }

        if (cls == Integer.class) {
            return ((Integer) value).floatValue();
        }

        if (cls == Long.class) {
            return ((Long) value).floatValue();
        }

        if (cls == Float.class) {
            return ((Float) value).floatValue();
        }

        if (cls == Double.class) {
            return ((Double) value).floatValue();
        }

        return Float.parseFloat(value.toString());
    }

    public static Double getDouble(Object value) {
        return getDouble(value, false);
    }

    public static Double getDouble(Object value, boolean useNull) {
        if (value == null) {
            if (useNull) {
                return null;
            }
            return 0D;
        }

        Class<?> cls = value.getClass();

        if (cls == Short.class) {
            return ((Short) value).doubleValue();
        }

        if (cls == Integer.class) {
            return ((Integer) value).doubleValue();
        }

        if (cls == Long.class) {
            return ((Long) value).doubleValue();
        }

        if (cls == Float.class) {
            return ((Float) value).doubleValue();
        }

        if (cls == Double.class) {
            return ((Double) value).doubleValue();
        }

        return Double.parseDouble(value.toString());
    }

    public static String getString(Object value) {
        return getString(value, false);
    }

    public static String getString(Object value, boolean useNull) {
        if (value == null) {
            if (useNull) {
                return null;
            }
            return new String();
        }

        return value.toString();
    }

    public static BigDecimal getBigDecimal(Object value) {
        return getBigDecimal(value, false);
    }

    public static BigDecimal getBigDecimal(Object value, boolean useNull) {
        if (value == null) {
            if (useNull) {
                return null;
            }
            return new BigDecimal("0");
        }

        return new BigDecimal(value.toString());
    }

    public static Date getDate(Object value) {
        return getDate(value, false);
    }

    public static Date getDate(Object value, boolean useNull) {
        if (value == null) {
            if (useNull) {
                return null;
            }
            return new Date(0);
        }

        if (value instanceof Date) {
            return (Date) value;
        }

        return new Date(0);
    }

    public static BaseMap getBaseMap(Object value) {
        return getBaseMap(value, false);
    }

    public static BaseMap getBaseMap(Object value, boolean useNull) {
        if (value == null) {
            if (useNull) {
                return null;
            }
            return new BaseMap();
        }

        return (BaseMap) value;
    }

    public static BaseMapList getBaseMapList(Object value) {
        return getBaseMapList(value, false);
    }

    public static BaseMapList getBaseMapList(Object value, boolean useNull) {
        if (value == null) {
            if (useNull) {
                return null;
            }
            return new BaseMapList();
        }

        return (BaseMapList) value;
    }

}

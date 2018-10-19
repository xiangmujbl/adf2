package com.jnj.adf.dataservice.adfcoreignite;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

//package com.jnj.adf.client.api;

//import com.jnj.adf.grid.utils.JsonUtils;
import java.util.Map;
import java.util.TreeMap;

public final class JsonObject {
    private Map<String, Object> values = new TreeMap();

    private JsonObject() {
    }

    public static final JsonObject append(String jsonValue) {
        JsonObject jo = new JsonObject();
        jo.values = new TreeMap();
        Map m = (Map)JsonUtils.jsonToObject(jsonValue, Map.class);
        if (m != null) {
            jo.values.putAll(m);
        }

        return jo;
    }

    public final JsonObject append(JsonObject value) {
        if (value != null) {
            this.values.putAll(value.values);
        }

        return this;
    }

    public static final JsonObject create() {
        JsonObject jo = new JsonObject();
        jo.values = new TreeMap();
        return jo;
    }

    public JsonObject append(String key, String value) {
        this.values.put(key, value);
        return this;
    }

    public JsonObject append(String key, Object value) {
        this.values.put(key, value);
        return this;
    }

    public String getValue(String key) {
        Object o = this.values.get(key);
        return o == null ? null : String.valueOf(o);
    }

    public <T> T getValue(String key, Class<T> ct) {
        Object o = this.values.get(key);
        return o == null ? null : (T)o;
    }

    public String toJson() {
        return JsonUtils.objectToJson(this.values);
    }

    public Map<String, Object> toMap() {
        return this.values;
    }

    public String toString() {
        return this.toJson();
    }
}

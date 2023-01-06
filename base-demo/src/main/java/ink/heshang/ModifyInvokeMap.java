package ink.heshang;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

/**
 * TODO
 *
 * @author 微信公众号《和尚的破功之路》
 * @date 2022/11/27 15:59
 * version: 1.0
 */
public class ModifyInvokeMap<K, V> implements Map<K, V> {
    private final Map<K, V>                               map;

    private final Consumer<ModifyInvokeMap<K, V>> onModified;

    private final Consumer<Triple<K, V, V>>               onPut;

    private final Consumer<Pair<K, V>>                    onRemove;

    private final Consumer<Map<? extends K, ? extends V>> onPutAll;

    private final Consumer<Void>                          onClear;

    private ModifyInvokeMap(Builder<K, V> builder) {
        map = builder.map;
        Consumer<Map<K, V>> onInit = builder.onInit;
        onModified = builder.onModified;
        onPut = builder.onPut;
        onRemove = builder.onRemove;
        onPutAll = builder.onPutAll;
        onClear = builder.onClear;

        if (onInit != null) {
            onInit.accept(map);
        }

        if (onModified != null) {
            onModified.accept(this);
        }
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public V get(Object key) {
        return map.get(key);
    }

    @Override
    public V put(K key, V value) {
        V old = map.put(key, value);
        if (onPut != null) {
            onPut.accept(new Triple<>(key, value, old) {
                @Override
                public Object getLeft() {
                    return null;
                }

                @Override
                public Object getMiddle() {
                    return null;
                }

                @Override
                public Object getRight() {
                    return null;
                }
            });
        }
        if (onModified != null) {
            onModified.accept(this);
        }
        return old;
    }

    @Override
    public V remove(Object key) {
        V remove = map.remove(key);
        if (onRemove != null) {
            onRemove.accept(new Pair<>((K) key, remove) {
                @Override
                public Object setValue(Object value) {
                    return null;
                }

                @Override
                public Object getLeft() {
                    return null;
                }

                @Override
                public Object getRight() {
                    return null;
                }
            });
        }

        if (onModified != null) {
            onModified.accept(this);
        }
        return remove;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        map.putAll(m);

        if (onPutAll != null) {
            onPutAll.accept(m);
        }

        if (onModified != null) {
            onModified.accept(this);
        }
    }

    @Override
    public void clear() {
        map.clear();
        if (onClear != null) {
            onClear.accept(null);
        }

        if (onModified != null) {
            onModified.accept(this);
        }
    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        return map.values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return map.entrySet();
    }

    public static final class Builder<K, V> {
        private Map<K, V>                               map;
        private Consumer<Map<K, V>>                     onInit;
        private Consumer<ModifyInvokeMap<K, V>>         onModified;
        private Consumer<Triple<K, V, V>>               onPut;
        private Consumer<Pair<K, V>>                    onRemove;
        private Consumer<Map<? extends K, ? extends V>> onPutAll;
        private Consumer<Void>                          onClear;

        public Builder() {
        }

        public Builder<K, V> map(Map<K, V> val) {
            map = val;
            return this;
        }

        public Builder<K, V> onInit(Consumer<Map<K, V>> val) {
            onInit = val;
            return this;
        }

        public Builder<K, V> onModified(Consumer<ModifyInvokeMap<K, V>> val) {
            onModified = val;
            return this;
        }

        public Builder<K, V> onPut(Consumer<Triple<K, V, V>> val) {
            onPut = val;
            return this;
        }

        public Builder<K, V> onRemove(Consumer<Pair<K, V>> val) {
            onRemove = val;
            return this;
        }

        public Builder<K, V> onPutAll(Consumer<Map<? extends K, ? extends V>> val) {
            onPutAll = val;
            return this;
        }

        public Builder<K, V> onClear(Consumer<Void> val) {
            onClear = val;
            return this;
        }

        public ModifyInvokeMap<K, V> build() {
            if (map == null) {
                throw new IllegalArgumentException("map 不能为空");
            }
            return new ModifyInvokeMap<K, V>(this);
        }
    }
}
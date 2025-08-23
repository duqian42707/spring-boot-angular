import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author duqian
 * @date 2025/8/22
 */
public class TestCache {
    Cache<Object, Object> cache = Caffeine.newBuilder()
            .initialCapacity(128)//初始大小
            .maximumSize(1024)//最大数量
            .expireAfterWrite(60, TimeUnit.SECONDS)//过期时间
            .build();

    @Test
    public void testGet() {
        cache.put("key1", "value1");

        Object key1 = cache.get("key1", k -> {
            System.out.println("Cache miss for key: " + k);
            return "defaultValue";
        });
        Object key2 = cache.get("key2", k -> {
            System.out.println("Cache miss for key: " + k);
            return "defaultValue";
        });
        System.out.println(key1);
        System.out.println(key2);
    }

    @Test
    public void testGetIfPresent() {
        cache.put("key1", "value1");

        Object key1 = cache.getIfPresent("key1");
        Object key2 = cache.getIfPresent("key2");
        System.out.println(key1);
        System.out.println(key2);
    }

}

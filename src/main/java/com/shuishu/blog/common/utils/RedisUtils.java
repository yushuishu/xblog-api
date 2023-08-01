package com.shuishu.blog.common.utils;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Author ：谁书-ss
 * @Date ：2023-03-09 21:54
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：Redis 工具类
 * <p></p>
 */
@Component
@RequiredArgsConstructor
@Validated
public class RedisUtils {

    private final RedisTemplate<String, Object> redisTemplate;



    // ======================================================== 公共的 ========================================================

    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     */
    public void expire(@NotBlank(message = "key不能为空") String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据key 获取过期时间
     *
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    public Long getExpire(@NotBlank(message = "key不能为空") String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断key 是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    public boolean hasKey(@NotBlank(message = "key不能为空") String key) {
        try {
            return Boolean.TRUE.equals(redisTemplate.hasKey(key));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据key 删除缓存
     *
     * @param key 可以传一个值 或多个
     */
    @SuppressWarnings("unchecked")
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete((Collection<String>) CollectionUtils.arrayToList(key));
            }
        }
    }


    //======================================================== String =========================================================


    /**
     * 普通缓存获取
     *
     * @param key 键
     * @return 值
     */
    public Object strGet(@NotBlank(message = "key不能为空") String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通缓存放入
     *
     * @param key   键
     * @param value 值
     */
    public void strSet(@NotBlank(message = "key不能为空") String key, @NotNull(message = "缓存值不能为空") Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 普通缓存放入并设置时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    public boolean strSet(@NotBlank(message = "key不能为空") String key, @NotNull(message = "缓存值不能为空") Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                strSet(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 递增
     *
     * @param key   键
     * @param delta 要增加几(大于0)
     * @return 在管道/事务中使用时为空。
     */
    public Long strIncrement(@NotBlank(message = "key不能为空") String key, @Min(value = 1, message = "增量最小1") long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 递减
     *
     * @param key   键
     * @param delta 要减少几(小于0)
     * @return 在管道/事务中使用时为空。
     */
    public Long strDecrement(@NotBlank(message = "key不能为空") String key, @Min(value = 1, message = "增量最小1") long delta) {
        return redisTemplate.opsForValue().decrement(key, -delta);
    }


    // ============================================================== list ================================================================


    /**
     * 获取list缓存的长度
     *
     * @param key 键
     * @return -
     */
    public Long listSize(@NotBlank(message = "key不能为空") String key) {
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    /**
     * 获取list缓存的内容
     * （start=0 AND end=-1 代表所有值）
     *
     * @param key   键
     * @param start 开始
     * @param end   结束
     * @return List<Object>
     */
    public List<Object> listGet(@NotBlank(message = "key不能为空") String key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 通过索引 获取list中的值
     *
     * @param key   键
     * @param index 索引  index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * @return -
     */
    public Object listGet(@NotBlank(message = "key不能为空") String key, long index) {
        try {
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return -
     */
    public boolean listSet(@NotBlank(message = "key不能为空") String key, @NotNull(message = "缓存值不能为空") Object value) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return true 存在 false不存在
     */
    public boolean listSet(@NotBlank(message = "key不能为空") String key, @NotNull(message = "缓存值不能为空") Object value, long time) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return 在管道/事务中使用时为空。
     */
    public boolean listSet(@NotBlank(message = "key不能为空") String key, @NotNull(message = "缓存值不能为空") List<Object> value) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     */
    public boolean listSet(@NotBlank(message = "key不能为空") String key, @NotNull(message = "缓存值不能为空") List<Object> value, long time) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据索引修改list中的某条数据
     *
     * @param key   键
     * @param index 索引
     * @param value 值
     * @return -
     */
    public boolean listUpdate(@NotBlank(message = "key不能为空") String key, @Min(value = -1, message = "索引数值最小值-1") long index, @NotNull(message = "更新的缓存值不能为空") Object value) {
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 移除N个值为value
     *
     * @param key   键
     * @param count 可以为正、负或零。
     *              正数：从列表左侧删除value的第一次计数出现次数。
     *              负数：则从列表右侧删除value的第一次计数出现次数。
     *              零：删除所有出现的值。
     * @param value 值
     * @return 移除的个数
     */
    public Long listRemove(@NotBlank(message = "key不能为空") String key, long count, @NotNull(message = "删除的值不能为空") Object value) {
        try {
            return redisTemplate.opsForList().remove(key, count, value);
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }


    // ======================================================== set ==========================================================

    /**
     * 根据key获取Set中的所有值
     *
     * @param key 键
     * @return -
     */
    public Set<Object> setGet(@NotBlank(message = "key不能为空") String key) {
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据value从一个set中查询,是否存在
     *
     * @param key   键
     * @param value 值
     * @return true 存在 false不存在
     */
    public Boolean setHasKey(@NotBlank(message = "key不能为空") String key, @NotNull(message = "缓存值不能为空") Object value) {
        try {
            return redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将数据放入set缓存
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public Long setSet(@NotBlank(message = "key不能为空") String key, Object... values) {
        try {
            return redisTemplate.opsForSet().add(key, values);
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    /**
     * 将数据放入set缓存
     *
     * @param key    键
     * @param time   时间(秒)
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public Long setSet(@NotBlank(message = "key不能为空") String key, long time, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().add(key, values);
            if (time > 0) {
                expire(key, time);
            }
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    /**
     * 获取set缓存的长度
     *
     * @param key 键
     * @return -
     */
    public Long setSize(@NotBlank(message = "key不能为空") String key) {
        try {
            return redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    /**
     * 移除值为value的
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    public Long setRemove(@NotBlank(message = "key不能为空") String key, Object... values) {
        try {
            return redisTemplate.opsForSet().remove(key, values);
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }


    //================================================================= Hash ==================================================================

    /**
     * 获取所有键值
     *
     * @param key redisKey（缓存key）
     * @return 对应的多个键值
     */
    public Map<Object, Object> hashGet(@NotBlank(message = "key不能为空") String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * HashGet
     *
     * @param key    redis缓存 key
     * @param mapKey 指定的 map key
     * @return 值
     */
    public Object hashGet(@NotBlank(message = "key不能为空") String key, @NotBlank(message = "mapKey不能为空") String mapKey) {
        return redisTemplate.opsForHash().get(key, mapKey);
    }

    /**
     * Hash表缓存 map
     *
     * @param key 键
     * @param map 对应多个键值
     * @return true 成功 false 失败
     */
    public boolean hashSet(@NotBlank(message = "key不能为空") String key, @NotNull(message = "缓存值不能为空") Map<String, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Hash表缓存 map， 并设置时间
     *
     * @param key  键
     * @param map  对应多个键值
     * @param time 时间(秒)
     * @return true成功 false失败
     */
    public boolean hashSet(@NotBlank(message = "key不能为空") String key, @NotNull(message = "缓存值不能为空") Map<String, Object> map, long time) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Hash表缓存一条数据，如果不存在将创建
     *
     * @param key      键
     * @param mapKey   mapKey
     * @param mapValue mapValue
     * @return true 成功 false失败
     */
    public boolean hashSet(@NotBlank(message = "key不能为空") String key,
                           @NotBlank(message = "mapKey不能为空") String mapKey,
                           @NotNull(message = "mapValue不能为空") Object mapValue) {
        try {
            redisTemplate.opsForHash().put(key, mapKey, mapValue);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Hash表缓存一条数据，如果不存在将创建。并设置时间
     *
     * @param key      key
     * @param mapKey   mapKey
     * @param mapValue mapValue
     * @param time     时间(秒)  注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return true 成功 false失败
     */
    public boolean hashSet(@NotBlank(message = "key不能为空") String key,
                           @NotBlank(message = "mapKey不能为空") String mapKey,
                           @NotNull(message = "缓存值不能为空") Object mapValue,
                           long time) {
        try {
            redisTemplate.opsForHash().put(key, mapKey, mapValue);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除hash表中的值
     *
     * @param key     键 不能为null
     * @param mapKeys map key 不能为null，可以多个
     */
    public void hashDel(@NotBlank(message = "key不能为空") String key, Object... mapKeys) {
        redisTemplate.opsForHash().delete(key, mapKeys);
    }

    /**
     * 判断hash表中是否有该项的值
     *
     * @param key    键 不能为null
     * @param mapKey 项 不能为null
     * @return true 存在 false不存在
     */
    public boolean hashHasKey(@NotBlank(message = "key不能为空") String key, @NotBlank(message = "mapKey不能为空") String mapKey) {
        return redisTemplate.opsForHash().hasKey(key, mapKey);
    }

    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     *
     * @param key            key
     * @param mapKey         map key
     * @param increaseNumber 要增加几(大于0)
     * @return -
     */
    public Double hashIncrement(@NotBlank(message = "key不能为空") String key,
                                @NotBlank(message = "mapKey不能为空") String mapKey,
                                double increaseNumber) {
        if (increaseNumber > 0) {
            return redisTemplate.opsForHash().increment(key, mapKey, increaseNumber);
        }
        return null;
    }

    /**
     * hash递减
     *
     * @param key            key
     * @param mapKey         map key
     * @param decreaseNumber 要减少记(小于0)
     * @return -
     */
    public Double hashDecrement(@NotBlank(message = "key不能为空") String key,
                                @NotBlank(message = "item不能为空") String mapKey,
                                double decreaseNumber) {
        if (decreaseNumber < 0) {
            return redisTemplate.opsForHash().increment(key, mapKey, -decreaseNumber);
        }
        return null;
    }


    // ================================================================== ZSet ==================================================================

    /**
     * 获取ZSet缓存的大小
     *
     * @param key 键
     * @return -
     */
    public Long zSetSize(@NotBlank(message = "key不能为空") String key) {
        try {
            return redisTemplate.opsForZSet().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    /**
     * 获取 ZSet 缓存内容
     *
     * @param key   键
     * @param start 开始
     * @param end   结束
     * @return Set<Object>
     */
    public Set<Object> zSetGet(@NotBlank(message = "key不能为空") String key, long start, long end) {
        try {
            return redisTemplate.opsForZSet().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return -
     */
    public boolean zSetSet(@NotBlank(message = "key不能为空") String key, @NotNull(message = "缓存值不能为空") Set<ZSetOperations.TypedTuple<Object>> value) {
        try {
            redisTemplate.opsForZSet().add(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return -
     */
    public boolean zSetSet(@NotBlank(message = "key不能为空") String key, @NotNull(message = "缓存值不能为空") Set<ZSetOperations.TypedTuple<Object>> value, long time) {
        try {
            redisTemplate.opsForZSet().add(key, value);
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 添加元素,有序集合是按照元素的score值由小到大排列
     *
     * @param key   键
     * @param value 对象名称
     * @param score 数据值
     * @return -
     */
    public Boolean zSetSet(@NotBlank(message = "key不能为空") String key, @NotNull(message = "缓存值不能为空") Object value, double score) {
        try {
            return redisTemplate.opsForZSet().add(key, value, score);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 从集合中删除指定元素
     *
     * @param key    键
     * @param values 删除的值 可以是多个
     * @return -
     */
    public Long zSetDel(@NotBlank(message = "key不能为空") String key, @NotNull(message = "缓存值不能为空") Object... values) {
        try {
            return redisTemplate.opsForZSet().remove(key, values);
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    /**
     * 增加元素的score值，并返回增加后的值
     *
     * @param key   键
     * @param value 值
     * @param delta 增加的score
     * @return -
     */
    public Double zSetIncrementScore(@NotBlank(message = "key不能为空") String key, @NotNull(message = "缓存值不能为空") Object value, double delta) {
        try {
            return redisTemplate.opsForZSet().incrementScore(key, value, delta);
        } catch (Exception e) {
            e.printStackTrace();
            return 0d;
        }
    }

    /**
     * 返回元素在集合的排名,有序集合是按照元素的score值由小到大排列
     *
     * @param key   键
     * @param value 值
     * @return 0表示第一位
     */
    public Long zSetRank(@NotBlank(message = "key不能为空") String key, @NotNull(message = "缓存值不能为空") Object value) {
        try {
            return redisTemplate.opsForZSet().rank(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}

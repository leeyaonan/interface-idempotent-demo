# 接口幂等性的实现方式

## 概念
* 幂等性：任意多次执行所产生的影响均与一次执行的影响相同

## 保证幂等性的几种方式
1. 数据库建立唯一索引，保证最终插入数据库的只有一条数据
2. token机制，每次接口请求前先获取一个token，然后再下次请求的时候，在请求头中加上这个token，后台进行验证，如果验证通过删除token，下次请求再次判断token
3. 悲观锁或者乐观锁，悲观锁可以保证每次for update的时候，其他sql无法update数据（在数据库引擎室innoDB的时候，select的条件必须是唯一索引，防止锁全表）
4. 先查询后判断，首先通过查询数据库是否存在数据，如果存在证明已经请求过了，直接拒绝该请求，如果没有存在，就证明是第一次进来，直接放行

### 采用SpringBoot + Redis实现接口幂等性
流程图：  
![img](https://schematic-diagram-1301192342.cos.ap-guangzhou.myqcloud.com/redis%E5%AE%9E%E7%8E%B0%E6%8E%A5%E5%8F%A3%E5%B9%82%E7%AD%89%E6%80%A7%E6%B5%81%E7%A8%8B%E5%9B%BE.png)
#### 方案一：使用拦截器拦截指定的请求
1. 搭建redis的服务API -> RedisUtils
2. 自定义注解
3. token创建与检验
4. 拦截器的配置

#### 方案二：使用Spring AOP对方法的请求参数校验
1. 搭建redis的服务API -> RedisUtils
2. 自定义注解
3. token创建与检验
4. 配置Aspect切面
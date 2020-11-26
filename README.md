# Java综合实战

## 集成MyBatisPlus
1. 在pom.xml文件引入相关的jar包依赖
2. 实现XxxMapper接口。通过此接口要操作数据持久化
3. 对XxxDO实体进行注解的定义，如：数据库表名，字段定义
4. 如需修改Plus默认配置，需实现MyBatisPlugsConfig类
5. 如需自定义一个方法，需要实现XxxxMapper.xml，来定义自定义SQL

## 自动更新系统级字段
1. 定义公共元数据处理器
2. 为XxxxDO配置注解

## 集成验证框架步骤
1. 在pom.xml引入相关的jar包支持
2. 在待验证的实体里面添加响应的注解
3. 在Controller中添加相应的注解
4. 参数校验工具类完成service层的参数校验

## 实现统一异常处理
1. 实现一个异常处理的类，并且用@ControllerAdvice修饰

## 集成CaffeineCache缓存功能
1. 学习注解：
    - @Cacheable：缓存数据，一般用在查询方法上。将查询到的数据进行缓存
    - @CachePut：更新方法上，将数据从缓存中进行更新
    - @CacheEvict：删除缓存
2. pom.xml文件引入cache相关jar包支持
3. CacheManager Bean
4. 使用注解，标识方法哪些需要缓存

注意：Caffeine是单机版本的缓存实现，不能再集群环境下使用，目前集群下主流是使用redis来做集群缓存

## 集成Guava令牌实现全局限流功能
1. pom.xml引入Guava工具包的支持
2. 定义一个拦截器，实现令牌的发放和获取
3. 将拦截器配置到web系统中
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

## 使用TraceId实现日志跟踪
1. 建立一个过滤器，在过滤器中给线程设置TraceId
2. 将日志配置文件进行修改，把TraceId打印到日志中

## 文件上传下载
1. 文件上传的Controller，负责处理文件上传
2. 文件上传的服务接口，通过接口的形式来定义出文件上传的功能
3. 实现文件上传的业务逻辑
4. 文件下载，采用静态路径映射的方式实现

## 集成EasyExcel实现数据导出功能
1. pom.xml把相关的jar配置好
2. UserController新增加导出的方法
3. 实现数据导出的功能
    - 定义导出实体
    - 分配加载数据，分批使用EasyExcel导出
    - 导出的文件上传
    
## 导出功能异步化
1. 创建线程池
2. 将导出方法使用@Aync注解标记为异步执行

## 使用Swagger2生成API文档
1. pom.xml引入jar包
2. 配置Swagger2的配置类
3. Controller及相关的实体中写对应的注解
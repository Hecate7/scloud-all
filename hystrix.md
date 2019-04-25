#hystrix

雪崩效应："基础服务故障"导致"级联故障"的现象。
###### 提供者不可用导致消费者不可用，并将不可用逐渐放大的过程

* 为网络请求设置超时，让资源尽快释放。
###### 一次远程调用对应着一个线程/进程，线程/进程对应着系统资源，响应太慢，得不到释放，资源被耗尽，最终导致服务不可用。

* 使用断路器模式。


## 
* 包裹请求
###### HystrixCommand
* 跳闸机制

* 资源隔离(舱壁模式)
###### 为每个依赖都维护了一个小型的线程池/信号量，如果该线程池已满，发往该依赖的请求就被立即拒绝，从而加速失败判定。

##### 线程隔离（默认）：在单独的线程上执行，并发请求受线程池中的线程数量限制。
###### 有一个除网络超时以外的额外保护层

##### 信号量隔离：在调用线程上执行，并发请求受信号量个数限制    开销远比线程池的开销少，但不能设置超时和异步访问，只有在依赖服务足够可靠的情况下才使用信号量
###### 只有当调用负载非常高时，才需要使用信号量隔离

###### 如果发生找不到上下文的运行时异常，可考虑将隔离策略设置为SEMAPHORE
execution.isolation.strategy = SEMAPHORE


* 监控

* 回退机制
###### fallbackMethod，执行回退逻辑并不代表断路器已经打开，请求失败、超时、被拒绝以及断路器打开都会执行回退逻辑
* 自我修复
###### 断路器打开一段时间过后，会自动进入半开状态。

##
## 工作流程
1.创建HystrixCommand（依赖的服务返回单个操作结果）或HystrixObservableCommand（依赖的结果返回多个操作结果）对象
###### 命令模式（Invoker，Command，Receiver）
2.命令执行
##### execute()-同步执行 + queue()-异步执行 / observe()-Hot Observable + toObservable()-Cold Observable
###### Hot Observable：无论事件源是否有订阅者，都会在创建后对事件进行发布，每一个订阅者都有可能从事件源的中途开始，只看到了整个操作的局部过程
###### Cold Observable：直到有订阅者后才发布事件，可以保证从一开始看到整个操作的全部过程
3.结果是否被缓存
###### YES-9/NO-4
4.断路器是否打开
###### 打开-fallback处理逻辑8/关闭-检查是否有可用资源5
5.线程池/请求队列/信号量是否占满
###### YES-fallback处理逻辑8/NO-请求依赖服务6
6.HystrixObservableCommand.construct()/创建HystrixCommand.run()
###### run()-返回单一结果或抛出异常
###### construct()-返回observable对象来发射多个结果或通过onError发送错误通知
##### 执行时间超过设置的超时阈值，抛出TimeoutException-8
7.计算断路器的健康度
###### 断路器使用“成功”、“失败”、“拒绝”、“超时”等统计数据决定是否将断路器打开，对请求进行“熔断/短路”
8.fallback处理-服务降级
##### 最终的降级逻辑一定不是一个依赖网络请求的处理
###### 实现降级逻辑：HystrixObservableCommand-resumeWithFallback()/创建HystrixCommand-getFallback()
9.返回成功的响应
##

## 断路器原理

public interface HystrixCircuitBreaker {

    /**判断请求是否被执行*/
    boolean allowRequest();
    /**判断当前断路器是否打开*/
    boolean isOpen();
    /**闭合断路器*/
    void markSuccess();
    
    /** ConcurrentHashMap<String, HystrixCircuitBreaker> circuitBreakersByCommand */
    static class Factory;
    /** 什么都不做的断路器实现，允许所有请求，断路器始终闭合 */
    static class NoOpCircuitBreaker;
    /** 实现类 */
    static class HystrixCircuitBreakerImpl;
}



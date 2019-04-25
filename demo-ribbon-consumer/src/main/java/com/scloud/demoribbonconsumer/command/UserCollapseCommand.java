package com.scloud.demoribbonconsumer.command;

import com.netflix.hystrix.HystrixCollapser;
import com.netflix.hystrix.HystrixCollapserKey;
import com.netflix.hystrix.HystrixCollapserProperties;
import com.netflix.hystrix.HystrixCommand;
import com.scloud.demoribbonconsumer.entity.User;
import com.scloud.demoribbonconsumer.service.UserService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 合并请求器
 */
public class UserCollapseCommand extends HystrixCollapser<List<User>,User,Long> {
    private UserService userService;
    private Long userId;

    public UserCollapseCommand(UserService userService, Long userId) {
        /**
         * withTimerDelayInMilliseconds:设置时间延迟属性
         * 在该窗口时间内收集获取单个User的请求并在时间窗结束时进行组合组装成单个批量请求
         */
        super(Setter.withCollapserKey(HystrixCollapserKey.Factory.asKey("userCollapseCommand"))
                .andCollapserPropertiesDefaults(HystrixCollapserProperties.defaultSetter().withTimerDelayInMilliseconds(100)));
        this.userService = userService;
        this.userId = userId;
    }

    @Override
    public Long getRequestArgument() {
        return userId;
    }

    /**
     * 合并请求产生批量命令的具体实现方法
     * CollapsedRequest<User, Long>：User(ResponseType),Long(RequestArgumentType)
     * @param collection
     * @return
     */
    @Override
    protected HystrixCommand<List<User>> createCommand(Collection<CollapsedRequest<User, Long>> collection) {
        /**
         * collection:保存了延迟时间窗中收集到的所有单个User的请求
         */
        List<Long> userIds = new ArrayList<>(collection.size());
        userIds.addAll(collection.stream().map(CollapsedRequest::getArgument).collect(Collectors.toList()));
        return new UserBatchCommand(userService,userIds);
    }

    /**
     * 批量命令结果返回后的处理，将批量结果拆分并传递给合并前的各个原子请求命令
     * UserBatchCommand实例被触发完成后，执行该方法
     * @param users：保存createCommand()中组织的批量请求命令的返回结果
     * @param collection：每个被合并的请求
     */
    @Override
    protected void mapResponseToRequests(List<User> users, Collection<CollapsedRequest<User, Long>> collection) {
        int count = 0;
        for (CollapsedRequest<User,Long> collapsedRequest : collection){
            User user = users.get(count++);
            /**
             * 为每个合并前的单个请求设置返回结果
             */
            collapsedRequest.setResponse(user);
        }
    }
}

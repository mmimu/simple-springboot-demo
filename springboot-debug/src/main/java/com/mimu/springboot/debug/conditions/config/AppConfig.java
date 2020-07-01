package com.mimu.springboot.debug.conditions.config;

import com.mimu.springboot.debug.conditions.config.condition.SimpleConditionRegisterBeanPhaseConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ConfigurationCondition;
import org.springframework.context.annotation.Import;

/**
 author: mimu
 date: 2020/5/18
 */
@Configuration(proxyBeanMethods = false)
@Import(value = {SimpleConditionRegisterBeanPhaseConfig.class/*, SimpleConditionParseConfigurationPhaseConfig.class*/})
public class AppConfig {

    /**
     * 在 spring 的 ConfigurationCondition 中共存在 两个 阶段的 condition 判断逻辑
     * 见 {@link ConfigurationCondition.ConfigurationPhase }
     * 1. PARSE_CONFIGURATION; 2.REGISTER_BEAN 与此对应的的 spring 容器对二者的判断逻辑处理 是在
     * 1.1.{@link ConfigurationClassParser#processConfigurationClass() 该方法中的 conditionEvaluator.shouldSkip() }
     * 即在 加载 被 @Configuration 标注的配置类的时候 进行 condition 逻辑判断
     * 2.2. {@link ConfigurationClassBeanDefinitionReader#loadBeanDefinitionsForBeanMethod(),
     * @link org.springframework.context.annotation.ConfigurationClassParser#doProcessConfigurationClass()
     * 方法中的conditionEvaluator.shouldSkip() }
     * 即在 生成 bean 实例 的时候 进行 condition 判断
     *
     * 在 1.1 中如果 shouldSkip() 返回 true 则 被 @Configuration 标注的 配置类不会被加载到spring 容器中
     * 在 2.2 中如果 shouldSkip() 返回 true 则 被 @Configuration 标注的 配置类会被加载到spring 容器中
     * 但是 该配置类中 被 @Bean 标注的方法 则会忽略即不会生成 方法对应的 实例
     *
     *
     *
     * 在 注解 Configuration 中 有一个  proxyBeanMethods() 属性，默认为 true 标志着在默认情况下
     * spring 容器会为 被 @Configuration 标注的 javaConfig 配置类生成 代理类 (cglib 方式)
     * 在spring 容器中 如果有 从外部访问javaConfig(被@configuration 标注的类)(即使用代理类) 的某些方法的需求
     * 则可以使用 默认的 true 值即可。如果没有这种调用的 需求
     * 则 可以 指定 proxyBeanMethods 为 false 可参见 本类的 Configuration 注解
     * 可加快 Spring 容器的 启动
     */
}

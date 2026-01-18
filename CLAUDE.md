# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## 项目概述

社区团购管理系统，基于 Spring Boot 2.6.x + Java 17 构建，使用内部框架 `ddf-common`（类似 wheel/deck）。

## 模块结构

```
group-purchase-manage/
├── api/          # API 定义模块（Request/Response DTO）
└── core/         # 核心业务模块（Controller、Service、Repository）
```

## 常用命令

```bash
# 完整构建
mvn clean package -DskipTests

# 运行测试
mvn test

# 构建指定模块
mvn clean install -pl api,core -am
```

## 架构模式

### 分层结构
- **Controller** (`controller/`): API 入口层
- **ApplicationService** (`application/`): 业务用例编排层
- **Repository** (`repository/`): 数据访问封装层
- **Mapper** (`mapper/`): MyBatis 数据库操作

### 设计模式
- **策略模式**: `LoginStrategyContext` 管理多种登录方式（`PasswordLoginStrategy`、`SmsCodeLoginStrategy`）
- **事件驱动**: 使用 Spring `ApplicationEventPublisher` 发布业务事件（`GroupPayEvent`、`GroupViewEvent`、`GroupOrderReturnEvent`）
- **转换器模式**: `*Convert` 类用于 DTO/Entity 转换

## 核心业务流程

### 参团与支付流程
1. 用户调用 `join()` 加入团购 → 创建 `GroupPurchaseItem` 记录
2. 发送 RocketMQ 延迟消息（2分钟）处理超时未支付
3. 延迟消息消费者 `GroupOrderDelayMessageConsumer` 检查支付状态
4. 用户调用 `simulationPay()` 模拟支付 → 发布 `GroupPayEvent`
5. 事件监听器 `GroupPayEventListener` 处理支付成功逻辑

### 订单超时处理
- 使用 RocketMQ 延迟消息（`RocketMQDelayTimeMapping.M2` = 2分钟）
- 消息 Topic: `DELAY_TOPIC`，Tag: `GroupOrder`

## 依赖服务

| 服务 | 用途 | 客户端类 |
|------|------|----------|
| User Service | 用户信息查询 | `UserClient` |
| SMS Service | 短信发送 | `SmsClient` |
| Push Service | 消息推送 | `PushMessageClient` |
| Mail Service | 邮件发送 | `MailClient` |

## 关键配置

### 启用特性
```java
@SpringBootApplication
@MapperScan("com.ddf.group.purchase.core.mapper")
@EnableAuthenticate        // JWT 认证
@EnableAsync               // 异步处理
@EnableScheduling          // 定时任务
@EnableLogAspect(slowTime = 3000)  // 访问日志（>3s 慢查询）
@EnableRateLimit()         // Redis 限流
@EnableRepeatable          // 防重复提交
public class GroupPurchaseApplication
```

### 异常码
- `ExceptionCode` 类定义了业务异常码
- 使用 `BusinessException` 抛出业务异常

## 代码规范

- 使用 `lombok` 简化代码（`@Data`、`@Builder`、`@Slf4j`）
- Mapper XML 文件位于 `core/src/main/resources/mapper/`
- 扩展 Mapper 使用 `*ExtMapper` 命名（如 `GroupPurchaseInfoExtMapper`）
- Repository 层封装数据访问逻辑

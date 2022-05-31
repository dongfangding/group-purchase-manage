YApi相关文档

# 项目设置
## 请求配置
* Pre-request Script(请求参数处理脚本)
```js
// 取出之前存储的token, 追加到满足项目的请求header中，这样可以快速在线调试api
var token=storage.getItem('group_token');
context.requestHeader.Authorization='Bearer ' + token
```

* Pre-response Script(响应数据处理脚本)
```js
// 将登录返回的token存储在本地，方便后面使用
if (context.pathname == '/group-purchase-manage/auth/login'){
    console.log(context.responseData.data.token)
    storage.setItem('group_token', context.responseData.data.token)
}
```

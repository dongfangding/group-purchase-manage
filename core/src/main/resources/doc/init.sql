create table community_base
(
    id                bigint auto_increment
        primary key,
    community_name    varchar(64)      not null comment '小区名称',
    community_address varchar(128)     null comment '小区地址',
    invalid_flag      bit default b'0' null comment '是否无效'
)
    comment '小区管理' collate = utf8mb4_unicode_ci;

create table group_purchase_good
(
    id                bigint auto_increment
        primary key,
    group_purchase_id bigint           not null comment '团购主表id',
    good_name         varchar(64)      not null comment '商品名称',
    good_description  varchar(255)     null comment '商品描述',
    price             decimal(11, 2)   not null comment '商品单价',
    stock             int              not null comment '商品库存',
    limit_type        varchar(32)      null comment '限制类型，默认不限购，如限购数量上限、起购数量',
    limit_value       bigint default 0 null comment '限制数量，与limit_type匹配使用',
    good_pic          varchar(255)     null comment '商品图片',
    specification     varchar(1024)    null comment '规格json数组， 第一层数组是规格名称，如尺码、口味； 第二层是具体规格的数值， 如尺码里有S/M/L',
    remaining_stock   int              not null comment '剩余商品库存'
)
    comment '团购商品表' collate = utf8mb4_unicode_ci;

create index IDX_group_id
    on group_purchase_good (group_purchase_id);

create table group_purchase_info
(
    id                   bigint auto_increment
        primary key,
    name                 varchar(128)     not null comment '团购名称',
    group_master_uid     bigint           not null comment '团长uid',
    status               int              not null comment '状态
1. 已创建
2. 已到货
3. 已完成
4. 已取消',
    content              varchar(3000)    null comment '团购富文本内容',
    ctime                bigint           not null comment '创建时间秒时间戳',
    mtime                bigint           not null comment '更新时间秒时间戳',
    public_flag          bit default b'0' null comment '是否公开到团购市场，不公开的话，不会在市场展示',
    wx_id_card_url       varchar(128)     null comment '个人微信名片二维码地址',
    pic_urls             varchar(1024)    null comment '图片附件地址，多个用逗号分隔',
    video_url            varchar(128)     null comment '视频附件地址',
    start_time           bigint           not null comment '团购开始时间',
    end_time             bigint           not null comment '团购结束时间',
    service_community_id bigint           null comment '服务小区'
)
    comment '团购主表信息' collate = utf8mb4_unicode_ci;

create index IDX_uid
    on group_purchase_info (group_master_uid);

create table group_purchase_item
(
    id                      bigint auto_increment
        primary key,
    order_no                varchar(64)      null comment '订单编号',
    group_purchase_id       bigint           not null comment '团购主表记录id',
    join_uid                bigint           not null comment '参团用户id',
    ctime                   bigint           not null comment '创建时间',
    subscribe_progress      bit default b'1' not null comment '是否订阅最新信息（状态和备注更新变化）',
    join_status             int              not null comment '支付状态 0 待支付 1 已支付 2 已退款  3 已完成',
    status_change_time      bigint           not null comment '状态变更时间',
    receiver_name           varchar(64)      null comment '收货人姓名',
    receiver_mobile         varchar(20)      null comment '收货人手机号',
    receiver_detail_address varchar(255)     null comment '收货人详细地址',
    receiver_province       varchar(32)      null comment '收货人省份',
    receiver_city           varchar(32)      null comment '收货人市',
    receiver_area           varchar(32)      null comment '收货人区',
    remark                  varchar(255)     null comment '备注',
    paid_flag               bit default b'0' null comment '是否已支付',
    pay_count_down_seconds  int default 0    null comment '支付倒计时秒',
    pay_time                bigint           null comment '支付时间戳',
    receiver_building_no    varchar(64)      null comment '收货人楼栋号',
    receiver_room_no        varchar(64)      null comment '收货人房间号'
)
    comment '用户参团记录' collate = utf8mb4_unicode_ci;

create index IDX_group_id
    on group_purchase_item (group_purchase_id);

create index IDX_join_uid
    on group_purchase_item (join_uid);

create table group_purchase_item_good
(
    id                       bigint auto_increment
        primary key,
    group_purchase_id        bigint         not null comment '团购主表记录id',
    group_purchase_item_id   bigint         not null comment '参团主表id',
    group_purchase_good_id   bigint         not null comment '团购商品表id',
    group_purchase_good_name varchar(64)    not null comment '购买商品名称',
    group_purchase_good_pic  varchar(255)   null comment '购买商品图片',
    join_uid                 bigint         not null comment '参团用户id',
    good_num                 int            not null comment '购买商品数量',
    ctime                    bigint         not null comment '购买时间',
    mtime                    bigint         not null comment '修改时间',
    price                    decimal(11, 2) null comment '商品单价',
    good_description         varchar(255)   null comment '购买商品描述',
    total_price              decimal(10, 2) null comment '商品总价'
)
    collate = utf8mb4_unicode_ci;

create table group_purchase_trace
(
    id                bigint auto_increment
        primary key,
    group_purchase_id bigint not null comment '团购记录id',
    status            int    not null comment '状态',
    ctime             bigint not null comment '变更时间'
)
    comment '团购状态跟踪' collate = utf8mb4_unicode_ci;

create index IDX_group_id
    on group_purchase_trace (group_purchase_id);

create table sys_dict
(
    id               bigint auto_increment
        primary key,
    dict_type_code   varchar(64)      not null comment '字典类型',
    dict_type_name   varchar(64)      not null comment '字典名称',
    dict_detail_code varchar(64)      not null comment '字典明细代码',
    dict_detail_name varchar(64)      not null comment '字段明细名称',
    request_value    varchar(64)      null comment '请求参数，比如这个字段后台映射为枚举的时候，那么字典是用来渲染的，但是请求的时候却是要用对应属性的枚举名，就是这个字段',
    sort             int default 0    not null comment '排序',
    active           bit default b'1' not null comment '是否有效'
)
    comment '字典表' collate = utf8mb4_unicode_ci;

create table user_address
(
    id             bigint auto_increment
        primary key,
    uid            bigint           not null comment '用户id',
    province       varchar(32)      null comment '省',
    city           varchar(32)      null comment '市',
    area           varchar(32)      null comment '区',
    detail_address varchar(255)     not null comment '详细地址',
    mobile         varchar(32)      not null comment '联系方式',
    receiver_name  varchar(32)      not null comment '收货人姓名',
    building_no    varchar(32)      null comment '楼号，10',
    room_no        varchar(32)      null comment '房号，如101',
    default_flag   bit default b'0' null comment '是否默认收货地址'
)
    collate = utf8mb4_unicode_ci;

create index IDX_uid
    on user_address (uid);

create table user_info
(
    id                             bigint auto_increment
        primary key,
    community_id                   int                       null comment '小区id',
    building_no                    varchar(32)               null comment '楼栋号',
    room_no                        varchar(32)               null comment '房间号',
    nickname                       varchar(32)  default ''   null comment '昵称',
    mobile                         varchar(64)               null comment '手机号',
    wx_id                          varchar(64)               null comment '微信名',
    wx_business_card_qrcode_url    varchar(128)              null comment '微信名片二维码图片地址',
    wx_collection_money_qrcode_url varchar(128)              null comment '微信收款二维码',
    temp_email                     varchar(64)               null comment '临时邮箱， 当邮箱未验证时存储在这个字段，当验证通过，再复制给正式邮箱字段，这样使用时方便',
    email                          varchar(64)               null comment '邮箱',
    email_verified                 bit          default b'0' null comment '邮箱是否已验证',
    avatar_url                     varchar(128) default ''   null comment '头像地址url',
    avatar_thumb_url               varchar(128) default ''   null comment '头像地址缩略图',
    password                       varchar(256)              null comment '登录密码',
    ctime                          bigint                    not null comment '注册秒时间戳'
)
    comment '注册用户信息' collate = utf8mb4_unicode_ci;

INSERT INTO group_purchase_manage.community_base (id, community_name, community_address, invalid_flag) VALUES (1, '同润山河小城', '上海市松江区古楼公路1198弄', false);

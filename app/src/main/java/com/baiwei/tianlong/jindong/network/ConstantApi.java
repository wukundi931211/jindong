package com.baiwei.tianlong.jindong.network;

public class ConstantApi {
    public static final String BASE_URL="https://www.zhaoapi.cn/";

    //首页首页广告（轮播图+京东秒杀+最底部的为你推荐
    public static final String HOME_AD = "ad/getAd";


    //首页子条目 分类
    public static final String HOME_CATAGORY = "product/getCatagory";

    //分类页面
    public static final String FEILEI_CATAGORY = "product/getCatagory";
    //子分类
    public static final String FEILEI_PRODUCT = "product/getProductCatagory";
    //搜索页面
    public static final String SEARCH_PRODUCTS = "product/searchProducts";

    //登录
    public static final String LOGIN = "user/login";
    //我的
    public static final String WO_AD = "ad/getAd";
    //注册
    public static final String REG = "user/reg";

    //修改个人信息页面
    public static final String UPDATE_NICKNAME = "user/updateNickName";
    //修改头像
    public static final String UPLOAD = "file/upload";
    //得到地址
    public static final String GET_ADDRS = "user/getAddrs";
    public static final String ADD_ADDR = "user/addAddr";
    public static final String UPDATE_ADDR = "user/updateAddr";
    public static final String SET_ADDR = "user/setAddr";
    public static final String GET_DEFAULT_ADDR = "user/getDefaultAddr";




    //查询购物车
    public static final String SHOPCAR_CARTS = "product/getCarts";
    //修改购物车
    public static final String SHOPCAR_UPDATECARTS = "product/updateCarts";
    public static final String SHOPCAR_AD = "ad/getAd";
    //删除购物车
    public static final String SHOPCAR_DELETECART = "product/deleteCart";
    //添加购物车
    public static final String PRODUCT_ADDCART = "product/addCart";



    //商品展示
    public static final String PRODUCT_INFO = "product/getProductDetail";


    //创建订单
    public static final String CREATE_ORDER = "product/createOrder";
    //订单页面
    public static final String GET_ORDERS = "product/getOrders";
    //修改订单
    public static final String UPDATE_ORDER = "product/updateOrder";
}

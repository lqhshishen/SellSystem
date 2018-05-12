package com.sell.liqihao.sellsystem.App.api;

import com.sell.liqihao.sellsystem.module.HomePage.bean.RecommendBean;
import com.sell.liqihao.sellsystem.module.Login.bean.ResultBean;
import com.sell.liqihao.sellsystem.module.Login.bean.UserBean;
import com.sell.liqihao.sellsystem.module.MyOrder.bean.OrderBean;
import com.sell.liqihao.sellsystem.module.allGoods.bean.ProductBean;
import com.sell.liqihao.sellsystem.module.shoppingCart.bean.CartBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by liqihao on 2018/1/10.
 */

public interface SellSystemiService {

    @FormUrlEncoded
    @POST("Login")
    Observable<UserBean>doLogin(@Field("username")String username, @Field("password")String password);

    @POST("loginTest")
    Call<String> getTest(@Body RequestBody requestBody );


    @FormUrlEncoded
    @POST("register")
    Observable<ResultBean> doRegister(@Field("username") String name,@Field("password") String pass,@Field("nickname") String nickName,@Field("tel")String tel);

    @GET("Product")
    Observable<List<ProductBean>> getProduct();

    @FormUrlEncoded
    @POST("cart")
    Observable<ResultBean> addToCart(@Field("productid")int productId,@Field("userid")int userId,@Field("number")int number);

    @FormUrlEncoded
    @POST("getCart")
    Observable<List<CartBean>> getCart(@Field("userid")int id);

    @FormUrlEncoded
    @POST("deleteCart")
    Observable<ResultBean> deleteCart(@Field("cartid")int cartid);

    @FormUrlEncoded
    @POST("search")
    Observable<List<ProductBean>> search(@Field("msg")String msg);

    @FormUrlEncoded
    @POST("accountManage")
    Observable<ResultBean> updateAddress(@Field("userid")int userId,@Field("address")String msg);

    @FormUrlEncoded
    @POST("accountManage")
    Observable<ResultBean> updatepass(@Field("userid")int userId,@Field("pass")String pass);

    @FormUrlEncoded
    @POST("order")
    Observable<ResultBean> buy(@Field("userid")int userId,@Field("orderid")int orderid
            ,@Field("note")String note,@Field("total")String total,@Field("time")String time);

    @FormUrlEncoded
    @POST("order")
    Observable<List<OrderBean>> allOrder(@Field("userid")int userId);

    @GET("today")
    Observable<List<RecommendBean>> getHomePage();

    @FormUrlEncoded
    @POST("finishOrder")
    Observable<ResultBean> finish(@Field("orderid")int orderid);

    @FormUrlEncoded
    @POST("findPass")
    Observable<ResultBean> forgetPass(@Field("userName")String userName,@Field("newPass")String pass);


//    /**
//     *绑定手机
//     * @param requestBody
//     * @return
//     */
//    @POST("v1/User/mobileBind")
//    Observable<TestBean> getMsg(@Body RequestBody requestBody);
//
//    /**
//     *获取分类列表
//     * @param requestBody
//     * @return
//     */
//    @POST("v1/Category/getList")
//    Observable<ClassificationBean>getList(@Body RequestBody requestBody);
//
//    /**
//     * 设置密码
//     * @param requestBody
//     * @return
//     */
//    @POST("v1/User/updatePass")
//    Observable<SetPasswordBean>upDatePassword(@Body RequestBody requestBody);
//
//    /**
//     *手机用户注册
//     * @param requestBody
//     * @return
//     */
//    @POST("v1/User/mobileReg")
//    Observable<MobileReg>mobileReg(@Body RequestBody requestBody);
//
//    /**
//     * 获取分类下小说列表接口
//     * @param requestBody
//     * @return
//     */
//    @POST("v1/Category/getBookList")
//    Observable<ClassicItemBean>getClassifyBookList(@Body RequestBody requestBody);
//
//    /**
//     * 发送手机验证码
//     * @param requestBody
//     * @return
//     */
//    @POST("v1/User/sendCode")
//    Observable<SendCodeBean>sendCode(@Body RequestBody requestBody);
//
//    /**
//     * 手机用户登录
//     * @param requestBody
//     * @return
//     */
//    @POST("v1/User/mobileLogin")
//    Observable<MobileLoginBean>login(@Body RequestBody requestBody);
//
//    /**
//     *我的书架(收藏)
//     * @param requestBody
//     * @return
//     */
//    @POST("v1/User/myBookshelf")
//    Observable<MyBookList>getMyBookList(@Body RequestBody requestBody);
//
//    /**
//     * 添加到书架(收藏)
//     * @param requestBody
//     * @return
//     */
//    @POST("v1/User/addBookshelf")
//    Observable<AddBookshelfBean>addToBookshelf(@Body RequestBody requestBody);
//
//    /**
//     * 删除书架(收藏)
//     * @param requestBody
//     * @return
//     */
//    @POST("v1/User/deleteBookshelf")
//    Observable<AddBookshelfBean>deleteBookshelf(@Body RequestBody requestBody);
//
//    /**
//     * 添加评论
//     * @param requestBody
//     * @return
//     */
//    @POST("v1/Comment/addComment")
//    Observable<AddComment>addComment(@Body RequestBody requestBody);
//
//    /**
//     * 小说评论列表
//     * @param requestBody
//     * @return
//     */
//    @POST("v1/Comment/commentList")
//    Observable<CommentList>showCommentList(@Body RequestBody requestBody);
//
//    /**
//     * 第三方用户登录
//     * @param requestBody
//     * @return
//     */
//    @POST("v1/User/login")
//    Observable<UMLoginBean>UMLogin(@Body RequestBody requestBody);
//
//    /**
//     * 热门搜索
//     * @param requestBody
//     * @return
//     */
//    @POST("v1/Piece/hotSearch")
//    Observable<HotSearchBean>hotSearch(@Body RequestBody requestBody);
//
//    /**
//     * 关键词搜索
//     * @param requestBody
//     * @return
//     */
//    @POST("v1/Piece/searchBook")
//    Observable<SearchBookBean>searchBook(@Body RequestBody requestBody);
//
//    /**
//     * 小说章节列表
//     * @param requestBody
//     * @return
//     */
//    @POST("v1/Novel/getList")
//    Observable<Chapter>getChapterList(@Body RequestBody requestBody);
//
//    /**
//     * 章节内容
//     * @param requestBody
//     * @return
//     */
//    @POST("v1/Novel/getDetail")
//    Observable<ChapterDetailBean>getChapterDetail(@Body RequestBody requestBody);
}

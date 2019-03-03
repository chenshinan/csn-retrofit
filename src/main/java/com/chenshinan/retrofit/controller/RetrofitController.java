package com.chenshinan.retrofit.controller;

import com.chenshinan.retrofit.config.RequestInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @author shinan.chen
 * @since 2019/3/3
 */
@RestController
public class RetrofitController {
    @GetMapping("/get")
    public String get() {
        System.out.println("success");
        return "get";
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        //创建 Retrofit 实例
        Retrofit retrofit = new Retrofit.Builder()
                //设置网络请求url地址
                .baseUrl("http://localhost:8081")
                //设置数据解析器
                .addConverterFactory(ScalarsConverterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
                //设置网络请求适配器
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        //创建网络请求接口实例
        RequestInterface requestInterface = retrofit.create(RequestInterface.class);
        //获取封装的请求
        Call<String> call = requestInterface.getCall();

//        //发送网络请求(同步)
//        try {
//            Response<String> response = call.execute();
//            System.out.println(response.body());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //发送网络请求(异步)
        call.enqueue(new Callback<String>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                //请求处理,输出结果
                System.out.println(response.body());
            }

            //请求失败时候的回调
            @Override
            public void onFailure(Call<String> call, Throwable throwable) {
                System.out.println("连接失败");
            }
        });

        return new ResponseEntity<>("test", HttpStatus.OK);
    }
}

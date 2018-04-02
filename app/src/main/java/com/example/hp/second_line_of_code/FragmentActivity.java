package com.example.hp.second_line_of_code;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;

/** 动态添加fragment：
 * 1.实现fragment1布局
 * 2. 继承fragemer类的fragment1类，重写onCreateView实现绑定布局
 * 3. 在activity对应布局中加载进实现的fragment布局，id和name要写对
 * 4. 利用fragmentmanager对区域的某块布局实现动态刷新fragment
 * @author liz
 * @version V1.0
 * @date 2018/3/28
 */
public class FragmentActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        Button btn_left_fragment=findViewById(R.id.btn_left_fragment);

        replaceFragment(new RightFragment());
        btn_left_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new ThirdFragment());
            }
        });



    }


    /**
     * 实现在某块区域自动刷新新的fragment
     * @param fragment
     */
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.frag_right_layout,fragment);
        transaction.addToBackStack(null); //控制添加fragment之后返回栈功能（返回之后退回到上一个fragment）
        transaction.commit();
    }
}

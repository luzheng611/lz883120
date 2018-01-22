package com.quzhao.Activity;

import android.animation.Animator;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.quzhao.Base.BaseActivity;
import com.quzhao.Helper.SpanHelpers.MyLinkMovementMethod;
import com.quzhao.Helper.SpanHelpers.WeiBoContentClickableSpan;
import com.quzhao.R;
import com.quzhao.Util.IUtils;
import com.quzhao.Util.LogUtil;
import com.quzhao.Util.ToastUtil;

/**
 * Created by Administrator on 2018/1/20.
 */


public class Register extends BaseActivity implements View.OnClickListener {
    private TextView code,getMid;//国家编码
    private EditText phone,mid,password1,password2;//手机号码,验证码，密码1，密码2
    private TextView next,midNext,commit;//下一步,获取验证码，提交注册
    private TextView xieyi;//用户协议
    private String CURRENT_CODE = "+86";//默认国家代码，中国
    private FrameLayout container;//根布局
    private LinearLayout phoneContainer,midContainer,passwordContainer;
    private LayoutTransition layoutTransition;//步骤动画管理器
    private CountDownTimer countDownTimer;//倒计时器
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        initTitle();


        initContainers();


        initPhoneContainer();

        initMidContainer();

        initPasswordContainer();
    }

    private void initPasswordContainer() {
        password1=findViewById(R.id.password1);
        password2=findViewById(R.id.password2);
        commit=findViewById(R.id.commit);
        commit.setOnClickListener(this);
        password2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(password1.getText().toString().trim().length()>=6&&password2.getText().toString().trim().length()>=6
                        &&password1.getText().toString().trim().equals(password2.getText().toString().trim())){
                    commit.setEnabled(true);
                }else{
                    if(commit.isEnabled()){
                        commit.setEnabled(false);
                    }
                }
            }
        });
    }

    private void initMidContainer() {
        countDownTimer=new CountDownTimer(60000,1000) {
            @Override
            public void onTick(long l) {
                getMid.setText("重获验证码"+String .valueOf(l/1000)+"秒");
            }

            @Override
            public void onFinish() {
                getMid.setEnabled(true);
                getMid.setText("重获验证码");
            }
        };
        mid=findViewById(R.id.mid);
        getMid=findViewById(R.id.getMid);
        midNext=findViewById(R.id.mid_next);
        midNext.setOnClickListener(this);
        getMid.setOnClickListener(this);
        mid.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.length()==6){
                    midNext.setEnabled(true);
                }else{
                    if(midNext.isEnabled()){
                        midNext.setEnabled(false);
                    }
                }
            }
        });
    }

    private void initContainers() {
        layoutTransition=new LayoutTransition();
        phoneContainer=findViewById(R.id.phone_container);
        midContainer=findViewById(R.id.mid_container);
        passwordContainer=findViewById(R.id.password_container);
        container=findViewById(R.id.container);
        setAnimator();
        container.setLayoutTransition(layoutTransition);
    }

    private void initPhoneContainer() {
        code = findViewById(R.id.code);
        phone = findViewById(R.id.phone);
        next = findViewById(R.id.next);
        xieyi = findViewById(R.id.xieyi);


        code.setText(CURRENT_CODE);
        SpannableString txt_xieyi = new SpannableString("注册即视为同意用户服务协议");
        txt_xieyi.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.main_color))
                , 7, txt_xieyi.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        txt_xieyi.setSpan(new WeiBoContentClickableSpan(this) {
            @Override
            public void onClick(View view) {
                // TODO: 2018/1/22 跳转用户协议
                ToastUtil.showToastShort("跳转用户协议");
            }
        }, 7, txt_xieyi.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        xieyi.setMovementMethod(new MyLinkMovementMethod());

        xieyi.setText(txt_xieyi);


        phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                boolean isMobile = IUtils.isPhone(editable.toString());
                LogUtil.e("手机号码？？：" + isMobile + "   字符长度：：：" + editable.length());
                if (editable.length() == 11 && isMobile && !IUtils.checkStrContainIllegal(editable.toString())) {
                    next.setEnabled(true);
                } else {
                    if (next.isEnabled()) {
                        next.setEnabled(false);
                    }
                }
            }
        });

        code.setOnClickListener(this);
        next.setOnClickListener(this);
    }

    private void initTitle() {
        back = findViewById(R.id.back);
        back.setOnClickListener(this);
        title = findViewById(R.id.title);
        title.setText("注册");
    }

    //自定义步骤动画
    private void setAnimator() {

        Animator appearAnim= ObjectAnimator.ofFloat(null,"translationX", getResources().getDisplayMetrics().widthPixels,0);
        appearAnim.setInterpolator(new DecelerateInterpolator());
        layoutTransition.setAnimator(LayoutTransition.APPEARING,appearAnim);

        Animator disappearAnim= ObjectAnimator.ofFloat(null,"translationX",0,-getResources().getDisplayMetrics().widthPixels);
        disappearAnim.setInterpolator(new DecelerateInterpolator());
        layoutTransition.setAnimator(LayoutTransition.DISAPPEARING,disappearAnim);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.code:
                break;
            case R.id.next:
                title.setText("填写验证码");
                phoneContainer.setVisibility(View.GONE);
                midContainer.setVisibility(View.VISIBLE);
                break;


            case R.id.getMid:
                getMid.setEnabled(false);
                countDownTimer.start();
                break;
            case R.id.mid_next:
                title.setText("设置密码");
                midContainer.setVisibility(View.GONE);
                passwordContainer.setVisibility(View.VISIBLE);
                break;

            case R.id.commit:
                ToastUtil.showToastShort("提交注册");
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(countDownTimer!=null){
            countDownTimer.cancel();
            countDownTimer=null;
        }
    }
}

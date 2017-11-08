package com.sell.liqihao.sellsystem.Login;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sell.liqihao.sellsystem.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    Activity mContext;

    @BindView(R.id.edt_password)
    EditText edtpassword;
    @BindView(R.id.edt_username)
    EditText edtusername;
    @BindView(R.id.login_btn_login)
    Button btnlogin;
    @BindView(R.id.login_btn_register)
    Button btnregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = LoginActivity.this;
        setContentView(R.layout.activity_login);
        ButterKnife.bind(mContext);
    }

    @OnClick({R.id.login_btn_register,R.id.login_btn_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_btn_register:
                Toast.makeText(mContext, "123", Toast.LENGTH_SHORT).show();
                break;
            case R.id.login_btn_login:
                Toast.makeText(mContext, "1234", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

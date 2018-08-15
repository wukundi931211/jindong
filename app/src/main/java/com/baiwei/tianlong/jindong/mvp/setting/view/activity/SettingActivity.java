package com.baiwei.tianlong.jindong.mvp.setting.view.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;


import com.baiwei.tianlong.jindong.R;
import com.baiwei.tianlong.jindong.base.BaseActivity;
import com.baiwei.tianlong.jindong.custom_view.MySearchView;
import com.baiwei.tianlong.jindong.mvp.setting.model.beans.UpdateNickNameBean;
import com.baiwei.tianlong.jindong.mvp.setting.model.beans.UploadBean;
import com.baiwei.tianlong.jindong.mvp.setting.presenter.SettingPresenter;
import com.baiwei.tianlong.jindong.mvp.setting.view.iview.ISettingView;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class SettingActivity extends BaseActivity<SettingPresenter> implements ISettingView {
    private static final String TAG = "SettingActivity--";
    //定义图片的路径
    private String path = Environment.getExternalStorageDirectory() + "/photo.png";
    @BindView(R.id.tv_userName_setting)
    TextView tvUserNameSetting;
    @BindView(R.id.tv_nickName_setting)
    TextView tvNickNameSetting;
    @BindView(R.id.tv_gender_setting)
    TextView tvGenderSetting;
    @BindView(R.id.ll_address_setting)
    LinearLayout llAddressSetting;
    @BindView(R.id.mtv_title_setting)
    MySearchView mtvTitleSetting;
    @BindView(R.id.ll_userIcon_setting)
    LinearLayout llUserIconSetting;
    @BindView(R.id.ll_userName_setting)
    LinearLayout llUserNameSetting;
    @BindView(R.id.ll_nickName_setting)
    LinearLayout llNickNameSetting;
    @BindView(R.id.ll_gender_setting)
    LinearLayout llGenderSetting;
    @BindView(R.id.ll_userBirthday_setting)
    LinearLayout llUserBirthdaySetting;
    @BindView(R.id.btn_cancel_setting)
    Button btnCancelSetting;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private PopupWindow pw;
    private TextView camera;
    private TextView pick;
    private TextView cancel;
    private String uid;

    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected SettingPresenter providePresenter() {
        return new SettingPresenter(this);
    }


    @Override
    protected void initView() {
        super.initView();
        Intent intent = getIntent();
        String nickname = intent.getStringExtra("nickname");
        String username = intent.getStringExtra("username");
        tvUserNameSetting.setText(username);
        tvNickNameSetting.setText(nickname);
    }

    @Override
    protected void initData() {
        super.initData();
        sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        boolean isLogin = sharedPreferences.getBoolean("isLogin", false);
        if (isLogin) {
            uid = sharedPreferences.getString("uid", "");
        } else {
            Toast.makeText(SettingActivity.this, "请登录", Toast.LENGTH_SHORT).show();

        }


        //加载PopupWindow的布局
        View popupView = View.inflate(this, R.layout.popup_window_item, null);
        //另一种新建方式
        pw = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //点击其他地方关闭
        pw.setOutsideTouchable(true);
        //给PopupWindow设置透明背景色
        pw.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        camera = popupView.findViewById(R.id.tv_camera_popup);
        pick = popupView.findViewById(R.id.tv_pick_popup);
        cancel = popupView.findViewById(R.id.tv_cancel_popup);

    }

    @Override
    protected void initListener() {
        super.initListener();
        mtvTitleSetting.setOnMyTitleViewClickListener(new MySearchView.OnMyTitleViewClickListener() {
            @Override
            public void leftClick() {
                Intent intent = new Intent();
                setResult(8, intent);
                finish();
            }

            @Override
            public void searhClick() {

            }

            @Override
            public void rightClick() {

            }
        });

        //相机
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //打开相机
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //将图片放到SD card
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(path)));
                startActivityForResult(intent, 100);
                pw.dismiss();
            }
        });
        //相册
        pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //打开相册
                Intent intent = new Intent(Intent.ACTION_PICK);
                //设置图片格式
                intent.setType("image/*");
                startActivityForResult(intent, 200);
                pw.dismiss();
            }
        });
        //取消
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pw.dismiss();
            }
        });
    }

    @OnClick({R.id.ll_userIcon_setting, R.id.ll_userName_setting, R.id.ll_nickName_setting, R.id.ll_gender_setting, R.id.ll_userBirthday_setting, R.id.btn_cancel_setting, R.id.ll_address_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_userIcon_setting:
                //相对于界面的位置
                pw.showAtLocation(llUserIconSetting, Gravity.BOTTOM, 0, 0);

                break;
            case R.id.ll_userName_setting:
                Toast.makeText(SettingActivity.this, "用户名暂不支持修改噢~", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_nickName_setting:
                String nickName = (String) tvNickNameSetting.getText();
                View view1 = View.inflate(this, R.layout.alert_dialog_view, null);
                final EditText editText = view1.findViewById(R.id.et_dialog_view);
                editText.setText(nickName);
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setIcon(R.mipmap.ic_launcher);
                builder1.setTitle("输入框");
                builder1.setMessage("请输入你要修改的昵称：\n");
                builder1.setView(view1);
                builder1.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String newNickName = editText.getText().toString();
                        tvNickNameSetting.setText(newNickName);

                        Map<String, String> map = new HashMap<>();
                        map.put("uid", uid);
                        map.put("nickname", newNickName);
                        presenter.updateNickName(map);
                    }
                });

                builder1.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder1.create().show();
                break;
            case R.id.ll_gender_setting:
                Toast.makeText(SettingActivity.this, "性别暂不支持修改噢~", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_userBirthday_setting:
                Toast.makeText(SettingActivity.this, "出生日期暂不支持修改噢~", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_address_setting:
                Intent intent = new Intent(SettingActivity.this, AddressActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.btn_cancel_setting:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setTitle("警示框");
                builder.setMessage("确定要注销此账号吗?");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        editor.clear();
                        editor.commit();
                        Intent intent = new Intent();
                        setResult(8, intent);
                        finish();
                    }
                });

                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.create().show();

                break;
        }
    }


    @Override
    public void updateNickNameSuccess(UpdateNickNameBean updateNickNameBean) {
        String msg = updateNickNameBean.getMsg();
        Toast.makeText(SettingActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateNickNameFailed(String error) {
        Log.d(TAG, "updateNickNameFailed: " + error);
    }

    @Override
    public void uploadPhotoSuccess(UploadBean uploadBean) {
        String msg = uploadBean.getMsg();
        Toast toast = Toast.makeText(SettingActivity.this, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        LinearLayout toastView = (LinearLayout) toast.getView();
        ImageView imageCodeProject = new ImageView(getApplicationContext());
        imageCodeProject.setImageResource(R.drawable.edit);
        toastView.addView(imageCodeProject, 0);

        toast.show();
    }

    @Override
    public void uploadPhotoFailed(String error) {
        Log.d(TAG, "uploadPhotoFailed: " + error);
        Toast.makeText(SettingActivity.this, error, Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 相机调用裁剪功能
        if (requestCode == 100 && resultCode == RESULT_OK) {
            Intent it = new Intent("com.android.camera.action.CROP");
            //得到拍完的照片进行裁剪
            it.setDataAndType(Uri.fromFile(new File(path)), "image/*");
            //设置是否支持裁剪
            it.putExtra("crop", true);
            //设置框的宽高比
            it.putExtra("aspactX", 1);
            it.putExtra("aspactY", 1);
            //设置输出的照片
            it.putExtra("outputX", 250);
            it.putExtra("outputY", 250);
            //将图片返回
            it.putExtra("return-data", true);

            startActivityForResult(it, 300);

        }

        //设置裁剪
        if (requestCode == 200 && resultCode == RESULT_OK) {
            //得到图片路径
            Uri uri = data.getData();
            //调用系统裁剪功能
            Intent it = new Intent("com.android.camera.action.CROP");
            //得到照片进行裁剪
            it.setDataAndType(uri, "image/*");
            //设置是否支持裁剪
            it.putExtra("crop", true);
            //设置框的宽高比
            it.putExtra("aspactX", 1);
            it.putExtra("aspactY", 1);
            //设置输出图片大小
            it.putExtra("outputX", 250);
            it.putExtra("outputY", 250);
            //将图片返回
            it.putExtra("return-data", true);

            startActivityForResult(it, 300);
        }
        //裁剪完后回到设置图片
        if (requestCode == 300 && resultCode == RESULT_OK) {
            Bitmap bitmap = data.getParcelableExtra("data");
            //获取文件路径
            File file = new File(getFilesDir().getAbsolutePath());
            if (!file.exists()) {
                //如果路径不存在就创建
                file.mkdirs();
            }
            //创建文件
            File file1 = new File(file, "photo.png");
            FileOutputStream fileOutputStream;
            try {
                //文件输出流
                fileOutputStream = new FileOutputStream(file1);
                //将bitmap写入文件流
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                //刷新此输出流并强制将所有缓冲的输出字节被写出
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            //RequestBody封装了文件和文件的类型
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file1);
            // MultipartBody.Part封装了接受的key和文件名字和RequestBody
            MultipartBody.Part part = MultipartBody.Part.createFormData("file", file1.getName(), requestBody);

            //调用上传头像
            presenter.uploadPhoto(uid, part);
        }

    }


    @Override
    public Context cotext() {
        return this;
    }
}

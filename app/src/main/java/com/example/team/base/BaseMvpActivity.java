package com.example.team.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Toast;

import com.example.team.mvp.CommonPresenter;
import com.example.team.mvp.ICommonModel;
import com.example.team.mvp.ICommonView;

import androidx.annotation.Nullable;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author : ZhiG
 * e-mail : 1120121044@163.com
 * date   : 2019/9/2610:31
 * desc   :
 * package: StuSpace:
 */
public abstract class BaseMvpActivity<M> extends BaseActivity implements ICommonView {

    private Unbinder bind;
    public CommonPresenter mpresenter;
    public M mModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(getLayoutId());
        bind = ButterKnife.bind(this);
        mpresenter = getPresenter();
        mModel=getModel();
        if (mpresenter!=null){
            mpresenter.attach(this, (ICommonModel) mModel);
        }
        initView();
        initData();
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int getLayoutId();

    protected abstract M getModel();

    protected abstract CommonPresenter getPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
        mpresenter.detach();
    }
    protected int getLoadaType(Object[] obj){
        return obj!=null&&obj.length>1? (int) obj[1] :0;
    }
    public void netErrorToast(Throwable e){
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
    }
}

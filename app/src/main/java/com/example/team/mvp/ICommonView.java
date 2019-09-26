package com.example.team.mvp;

/**
 * author : ZhiG
 * e-mail : 1120121044@163.com
 * date   : 2019/9/2619:01
 * desc   :
 * package: StuSpace:
 */
public interface ICommonView<T> {

    void onError(int whichApi, Throwable e);

    void onResponse(int whichApi, T... t);
}

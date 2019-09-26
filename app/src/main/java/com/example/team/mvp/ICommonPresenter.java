package com.example.team.mvp;

/**
 * author : ZhiG
 * e-mail : 1120121044@163.com
 * date   : 2019/9/2619:15
 * desc   :
 * package: StuSpace:
 */
public interface ICommonPresenter<T> {
    void getData(int whichApi,T...t);
}

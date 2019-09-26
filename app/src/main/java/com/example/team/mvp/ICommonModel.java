package com.example.team.mvp;

/**
 * author : ZhiG
 * e-mail : 1120121044@163.com
 * date   : 2019/9/2619:07
 * desc   :
 * package: StuSpace:
 */
public interface ICommonModel<T> {
    void getData(ICommonView view, int whcihApi, T... t);
}

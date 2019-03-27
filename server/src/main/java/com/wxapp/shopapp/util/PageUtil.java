package com.wxapp.shopapp.util;

import lombok.Data;

@Data
public class PageUtil {

    //已知数据
    private int number;    //当前页,从请求那边传过来。
    private int size;    //每页显示的数据条数。
    private int totalElements;    //总的记录条数。查询数据库得到的数据

    //需要计算得来
    private int totalPages;    //总页数，通过totalRecord和pageSize计算可以得来
    //开始索引，也就是我们在数据库中要从第几行数据开始拿，有了startIndex和pageSize，
    //就知道了limit语句的两个数据，就能获得每页需要显示的数据了
    private int startIndex;


    //将每页要显示的数据放在list集合中
    private Object content;

    //分页显示的页数,比如在页面上显示1，2，3，4，5页，start就为1，end就为5，这个也是算过来的
    private int start;
    private int end;

    //通过pageNum，size，totalRecord计算得来tatalPage和startIndex
    //构造方法中将pageNum，size，totalRecord获得
    public PageUtil(int pageNum, int pageSize, int totalElements) {
        this.number = pageNum;
        this.size = pageSize;
        this.totalElements = totalElements;

        //totalPages 总页数
        if (totalElements % pageSize == 0) {
            //说明整除，正好每页显示pageSize条数据，没有多余一页要显示少于pageSize条数据的
            this.totalPages = totalElements / pageSize;
        } else {
            //不整除，就要在加一页，来显示多余的数据。
            this.totalPages = totalElements / pageSize + 1;
        }
        //开始索引
        this.startIndex = (pageNum - 1) * pageSize;
        //显示5页，这里自己可以设置，想显示几页就自己通过下面算法修改
        this.start = 1;
        this.end = 5;
        //显示页数的算法

        if (totalPages <= 5) {
            //总页数都小于5，那么end就为总页数的值了。
            this.end = this.totalPages;
        } else {
            //总页数大于5，那么就要根据当前是第几页，来判断start和end为多少了，
            this.start = pageNum - 2;
            this.end = pageNum + 2;

            if (start < 0) {
                //比如当前页是第1页，或者第2页，那么就不如和这个规则，
                this.start = 1;
                this.end = 5;
            }
            if (end > this.totalPages) {
                //比如当前页是倒数第2页或者最后一页，也同样不符合上面这个规则
                this.end = totalPages;
                this.start = end - 5;
            }
        }
    }

}

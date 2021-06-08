package com.scl.basekt.test

/**
 * Created by admin on 2021/6/7.
 * name:
 */
data class PersonalCenterInfo(
    val contactUs: String,                          //联系我们
    val usingHelpUrl: String,                       //使用帮助地址
    val aboutUsUrl: String,                         //关于我们地址
    val userAgreementUrl: String,                   //用户协议地址
    val privacyAgreementUrl: String,                //隐私协议地址
    val encyclopediaUrl: String,                    //饮料百科地址
    val drinkingWaterAnalysisUrl: String,           //喝水分析地址
    val articleDetailsUrl: String,                  //文章详情地址
    val punchClockUrl: String,                      //打卡页面地址
    val clockCalendarUrl: String,                   //打卡日历地址
    val vipAgreementUrl :String?,                    //VIP会员协议地址
    val aiHistoryUrl :String                        //Ai识图历史
)

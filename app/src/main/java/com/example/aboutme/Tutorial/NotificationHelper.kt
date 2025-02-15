package com.example.aboutme.Tutorial

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.ContextWrapper
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.aboutme.R

class NotificationHelper(base : Context?) : ContextWrapper(base) {
    //상단바 알람 설정

    //채널 변수 만들기
    private val channelID: String = "channelID"
    private val channelNm: String = "channelName"

    init {
        //안드로이드 버전이 오레오보다 크면
        //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //채널 생성
            createChannel()
        //}
    }

    //채널 생성 함수
    private fun createChannel() {
        //객체 생성
        val channel: NotificationChannel =
            NotificationChannel(channelID, channelNm, NotificationManager.IMPORTANCE_DEFAULT)
        //설정
        channel.enableLights(true) //빛
        channel.enableVibration(true) //진동
        channel.lightColor = Color.RED
        channel.lockscreenVisibility = Notification.VISIBILITY_PRIVATE

        //생성
        getManager().createNotificationChannel(channel)
    }

    //매니저 생성
    fun getManager(): NotificationManager {

        return getSystemService(NOTIFICATION_SERVICE) as NotificationManager
    }

    //Notification 설정
    fun getChannelNotification(title: String, message: String): NotificationCompat.Builder {

        return NotificationCompat.Builder(applicationContext, channelID)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(R.drawable.noti_iconsmall)
        //아이콘은 흰색으로만 나온다
    }
}
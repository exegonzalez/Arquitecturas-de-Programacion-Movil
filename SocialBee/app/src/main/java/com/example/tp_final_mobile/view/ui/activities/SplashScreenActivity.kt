package com.example.tp_final_mobile.view.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.tp_final_mobile.R
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val animacion = AnimationUtils.loadAnimation(this, R.anim.animation)
        ivLogoBeerConf.startAnimation(animacion)

        val intent = Intent(this, MainActivity::class.java)

        animacion.setAnimationListener(object: Animation.AnimationListener{
            override fun onAnimationStart(p0: Animation?) {
            }

            override fun onAnimationRepeat(p0: Animation?) {
            }

            override fun onAnimationEnd(p0: Animation?) {
                startActivity(intent)
                finish()
            }

        })
    }
}
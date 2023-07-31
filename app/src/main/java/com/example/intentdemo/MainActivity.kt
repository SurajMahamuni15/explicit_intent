package com.example.intentdemo

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.example.intentdemo.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    val imageViewModel by viewModels<ImageViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        imageViewModel.uriPath.observe(this) {
            Log.e("uri", it.path.toString())
            Glide
                .with(this)
                .load(it)
                .centerCrop()
                .into(binding.imageView);
        }
        binding.button.setOnClickListener {
            Intent(Intent.ACTION_MAIN).also {
                it.`package` = "com.google.android.youtube"
                try {
                    startActivity(it)
                } catch (e: ActivityNotFoundException) {
                    e.printStackTrace()
                }
            }
        }

        binding.button2.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_EMAIL, "surajmaha00@gmail.com")
                putExtra(Intent.EXTRA_SUBJECT, "this is my subject")
                putExtra(Intent.EXTRA_TEXT, "demo text")

            }
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent?.getParcelableExtra(Intent.EXTRA_STREAM, Uri::class.java)
        } else {
            intent?.getParcelableExtra(Intent.EXTRA_STREAM)
        }
        Log.e("uri", uri.toString())
        imageViewModel.setNewUri(uri!!)
    }
}
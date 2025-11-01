package com.simats.painfreeyoga

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.simats.painfreeyoga.api.RetrofitClient.BASE_URL

class VideoPlayerActivity : AppCompatActivity() {

    private lateinit var playerView: PlayerView
    private var player: ExoPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)

        playerView = findViewById(R.id.playerView)

        val videoUrl = BASE_URL + intent.getStringExtra("VIDEO_URL")

        videoUrl?.let {
            player = ExoPlayer.Builder(this).build().also { exoPlayer ->
                playerView.player = exoPlayer
                val mediaItem = MediaItem.fromUri(Uri.parse(it))
                exoPlayer.setMediaItem(mediaItem)
                exoPlayer.prepare()
                exoPlayer.playWhenReady = true
            }
        }
    }

    override fun onStop() {
        super.onStop()
        player?.release()
        player = null
    }
}

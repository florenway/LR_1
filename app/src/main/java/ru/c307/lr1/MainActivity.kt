package ru.c307.lr1

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    private lateinit var nameText: TextView
    private lateinit var groupText: TextView
    private lateinit var centerImage: ImageView
    private lateinit var toggleLabelsButton: MaterialButton

    private var labelsVisible = true
    private var imageVisible = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.root)) { view, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(bars.left, bars.top, bars.right, bars.bottom)
            insets
        }

        nameText = findViewById(R.id.nameText)
        groupText = findViewById(R.id.groupText)
        centerImage = findViewById(R.id.centerImage)
        toggleLabelsButton = findViewById(R.id.toggleLabelsButton)
        val toggleImageButton: MaterialButton = findViewById(R.id.toggleImageButton)

        if (savedInstanceState != null) {
            labelsVisible = savedInstanceState.getBoolean(KEY_LABELS, true)
            imageVisible = savedInstanceState.getBoolean(KEY_IMAGE, true)
        }
        applyLabelsVisibility()
        applyImageVisibility()

        toggleLabelsButton.setOnClickListener {
            labelsVisible = !labelsVisible
            applyLabelsVisibility()
        }
        toggleImageButton.setOnClickListener {
            imageVisible = !imageVisible
            applyImageVisibility()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(KEY_LABELS, labelsVisible)
        outState.putBoolean(KEY_IMAGE, imageVisible)
    }

    private fun applyLabelsVisibility() {
        val visibility = if (labelsVisible) View.VISIBLE else View.GONE
        nameText.visibility = visibility
        groupText.visibility = visibility
        toggleLabelsButton.setText(if (labelsVisible) R.string.hide_labels else R.string.show_labels)
    }

    private fun applyImageVisibility() {
        centerImage.visibility = if (imageVisible) View.VISIBLE else View.INVISIBLE
    }

    companion object {
        private const val KEY_LABELS = "labels_visible"
        private const val KEY_IMAGE = "image_visible"
    }
}

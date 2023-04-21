package com.lb.vector_child_finder_sample

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.RippleDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.GestureDetectorCompat
import com.lb.vector_child_finder_library.*
import com.lb.vector_child_finder_sample.databinding.ActivityMainBinding
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var eyesGroup: VectorDrawableCompat.VGroup
    private lateinit var mountFullPath: VectorDrawableCompat.VFullPath
    private lateinit var binding: ActivityMainBinding
    private lateinit var skinFullPaths: ArrayList<VectorDrawableCompat.VFullPath>

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val vectorChildFinder = VectorChildFinder(this, R.drawable.boy, binding.image)
        binding.image.setOnTouchListener { _, event ->
            vectorChildFinder.vectorDrawable.handleTouchedPath(event,true)
            true
        }
        val pathNameToVFullPathMap = vectorChildFinder.pathNameToVFullPathMap
        skinFullPaths = pathNameToVFullPathMap["skin"]!!
        mountFullPath = vectorChildFinder.findPathByName("mouth")!!
        eyesGroup = vectorChildFinder.findGroupByName("eyes_group")!!
        val shirtFullPath = vectorChildFinder.findPathByName("dress_path")!!
        shirtFullPath.setOnClickListener {
            Toast.makeText(this, "shirt clicked!", Toast.LENGTH_SHORT).show()
        }

        binding.redColorButton.setOnClickListener {
            colorize(android.R.color.holo_red_light)
        }
        binding.purpleColorButton.setOnClickListener {
            colorize(android.R.color.holo_purple)
        }
        binding.greenColorButton.setOnClickListener {
            colorize(android.R.color.holo_green_dark)
        }
        binding.blueColorButton.setOnClickListener {
            colorize(android.R.color.holo_blue_dark)
        }

    }

    private fun colorize(@ColorRes colorResId: Int) {
        val color = ResourcesCompat.getColor(resources, colorResId, null)
        when {
            binding.eyesButton.isChecked -> {
                eyesGroup.mChildren?.let { children ->
                    children.forEach {
                        if (it is VectorDrawableCompat.VFullPath)
                            it.fillColor = color
                    }
                    binding.image.invalidate()
                }
            }

            binding.mouthButton.isChecked -> {
                mountFullPath.fillColor = color
                binding.image.invalidate()
            }

            binding.skinButton.isChecked -> {
                skinFullPaths.forEach {
                    it.fillColor = color
                }
                binding.image.invalidate()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var url: String? = null
        when (item.itemId) {
            R.id.menuItem_all_my_apps -> url =
                "https://play.google.com/store/apps/developer?id=AndroidDeveloperLB"

            R.id.menuItem_all_my_repositories -> url = "https://github.com/AndroidDeveloperLB"
            R.id.menuItem_current_repository_website -> url =
                "https://github.com/AndroidDeveloperLB/VectorChildFinder"
        }
        if (url == null)
            return true
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY or Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
        startActivity(intent)
        return true
    }
}

package com.alexgabor.motionplayground.scrollableheader

import android.os.Bundle
import android.transition.Transition
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexgabor.motionplayground.R
import com.alexgabor.motionplayground.databinding.ScrollableHeaderActivityBinding
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.scrollable_header_activity.view.*

class ScrollableHeaderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ScrollableHeaderActivityBinding>(this, R.layout.scrollable_header_activity)


        binding.list.apply {
            layoutManager = DisableScrollLinearLayoutManager(context)
            adapter = ScrollableHeaderAdapter().apply {
                submitList(items)
            }
        }

        binding.motionLayout.addTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {
            }

            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
            }

            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {
                print("Progress: ${p3}")


            }

            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
                binding.swipe.isEnabled = binding.motionLayout.progress == 0f

            }
        })

        binding.root.postDelayed({
            binding.header.isVisible = true
            binding.motionLayout.progress = 0f
        }, 4000)

        Glide.with(this)
            .load("https://picsum.photos/1600/900")
            .into(binding.header)

    }
}

val items = (0..50).map { ScrollableHeaderAdapter.Item("Title $it", "Description $it") }
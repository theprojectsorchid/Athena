/*
 * SPDX-FileCopyrightText: 2023 Sebastiano Barezzi
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.sebaubuntu.athena

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dev.sebaubuntu.athena.ui.SectionsPagerAdapter
import dev.sebaubuntu.athena.sections.Section

class MainActivity : AppCompatActivity() {
    // Views
    private val tabLayout by lazy { findViewById<TabLayout>(R.id.tabLayout) }
    private val toolbar by lazy { findViewById<MaterialToolbar>(R.id.toolbar) }
    private val viewPager2 by lazy { findViewById<ViewPager2>(R.id.viewPager2) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        viewPager2.adapter = SectionsPagerAdapter(this)

        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            val section = Section.sections[position]!!
            tab.setText(section.name)
            tab.icon = AppCompatResources.getDrawable(this, section.icon)
            tab.setContentDescription(section.description)
        }.attach()
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (viewPager2.currentItem == 0) {
            // If the user is currently looking at the first step, allow the system to handle
            // the Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed()
        } else {
            // Otherwise, go to the first page.
            viewPager2.currentItem = 0
        }
    }

    companion object {
        private const val LOG_TAG = "Athena"
    }
}

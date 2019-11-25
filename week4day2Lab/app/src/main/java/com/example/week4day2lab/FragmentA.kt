package com.example.week4day2lab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.TouchDelegate
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_a_main.*
import kotlinx.android.synthetic.main.fragment_b_main.*

class FragmentA: Fragment() {




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.fragment_a_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val startWindow = activity as MainActivity?
        var randomer = startWindow!!.mathNumber
        FragmentView.text = randomer.toString()
        }


}
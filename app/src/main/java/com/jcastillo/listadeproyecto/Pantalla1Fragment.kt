package com.jcastillo.listadeproyecto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.navigation.Navigation
import com.jcastillo.listadeproyecto.databinding.ActivityMainBinding

class Pantalla1Fragment : Fragment() {
    //lateinit var banding = Activ
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
      val view = inflater.inflate(R.layout.fragment_pantalla1,container, false)

        return view
    }
}
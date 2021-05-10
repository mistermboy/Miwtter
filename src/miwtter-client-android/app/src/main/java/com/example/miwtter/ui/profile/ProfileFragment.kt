package com.example.miwtter.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.miwtter.R
import es.uniovi.miw.miwtter.persistence.Settings

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.profile_fragment, container, false)

        view.findViewById<TextView>(R.id.name_surname_txt).text = Settings(requireContext()).name + " "+  Settings(requireContext()).surname
        view.findViewById<TextView>(R.id.name_txt).text = Settings(requireContext()).name
        view.findViewById<TextView>(R.id.surname_txt).text = Settings(requireContext()).surname
        view.findViewById<TextView>(R.id.username_txt).text = Settings(requireContext()).username
        return view
    }
}
package edu.itesm.modelosdefilasdeespera.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import edu.itesm.modelosdefilasdeespera.R
import edu.itesm.modelosdefilasdeespera.databinding.FragmentMainMenuBinding

class MainMenuFragment : Fragment() {

    private var _binding: FragmentMainMenuBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment with binding
        _binding = FragmentMainMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.CardViewMM1.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_mainMenuFragment_to_MM1MenuFragment)
        }

        binding.CardViewMMs.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_mainMenuFragment_to_MMSMenuFragment)
        }

        binding.CardViewMMsK.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_mainMenuFragment_to_MMsKMenuFragment)
        }

        binding.CardVIewMG1.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_mainMenuFragment_to_MG1MenuFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
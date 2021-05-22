package edu.itesm.modelosdefilasdeespera.viewmodel

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import edu.itesm.modelosdefilasdeespera.R

class MM1ModelFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_m_m1_model, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val args : MM1ModelFragmentArgs by navArgs()

        val lambda = args.lambda
        val mu = args.mu
        val cs = args.cw
        val cw = args.cs

        Log.i("lamda", args.lambda)
        Log.i("mu", args.mu)
        Log.i("cw", args.cw)
        Log.i("cs", args.cs)
    }

}
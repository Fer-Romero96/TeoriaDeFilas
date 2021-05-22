package edu.itesm.modelosdefilasdeespera.viewmodel

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import edu.itesm.modelosdefilasdeespera.R

class MG1ModelFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_m_g1_model, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val args : MG1ModelFragmentArgs by navArgs()

        val lambda = args.lambda
        val mu = args.mu
        val s = args.s
        val k = args.k
        val cs = args.cs
        val cw = args.cw

        Log.i("lamda", args.lambda)
        Log.i("mu", args.mu)
        Log.i("s", args.s)
        Log.i("k", args.k)
        Log.i("cs", args.cs)
        Log.i("cw", args.cw)

    }

}
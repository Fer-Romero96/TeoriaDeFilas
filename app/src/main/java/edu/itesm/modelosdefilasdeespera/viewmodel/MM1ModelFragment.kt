package edu.itesm.modelosdefilasdeespera.viewmodel

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import edu.itesm.modelosdefilasdeespera.databinding.FragmentMM1ModelBinding
import kotlin.math.pow

class MM1ModelFragment : Fragment() {

    private var _binding : FragmentMM1ModelBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMM1ModelBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val args : MM1ModelFragmentArgs by navArgs()

        val lambda = args.lambda.toDouble()
        val mu = args.mu.toDouble()
        val n = args.n.toLong()
        val cs = args.cs.toDouble()
        val cw = args.cw.toDouble()

        Log.i("lamba", lambda.toString())
        Log.i("mu", mu.toString())
        Log.i("n", n.toString())
        Log.i("cs", cs.toString())
        Log.i("cw", cw.toString())

        val p = lambda/mu
        binding.tvmm1rho.text = p.toString()

        val p0 = 1 - p
        binding.tvmm1p0.text = p0.toString()

        val pn = (1 - p) * p.pow(n.toDouble())
        binding.tvmm1pn.text = pn.toString()

        val lq = lq(lambda, mu)
        binding.tvmm1lq.text = lq.toString()

        val l = l(lambda, mu)
        binding.tvmm1l.text = l.toString()

        val wq = wq(lq, lambda)
        binding.tvmm1wq.text = wq.toString()

        val w = w(l, lambda)
        binding.tvmm1w.text = w.toString()

        val ct = ct(lq, cs, cw)
        binding.tvmm1ct.text = ct.toString()

        Log.i("lq",lq.toString())
        Log.i("l",l.toString())
        Log.i("wq",wq.toString())
        Log.i("w",w.toString())
        Log.i("ct",ct.toString())

    }

    private fun lq(lambda : Double, mu : Double) : Double {
        return lambda.pow(2) /mu*(mu-lambda)
    }

    private fun l(lambda : Double, mu : Double) : Double {
        return lambda/(mu-lambda)
    }

    private fun wq(lq : Double, lambda: Double) : Double {
        return lq/lambda
    }

    private fun w(l : Double, lambda: Double) : Double {
        return  l/lambda
    }

    private fun ct(lq: Double, cs: Double, cw : Double) : Double {
        return (lq * cw) + cs
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding =  null
    }
}
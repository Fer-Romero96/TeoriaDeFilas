package edu.itesm.modelosdefilasdeespera.viewmodel

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import edu.itesm.modelosdefilasdeespera.databinding.FragmentMG1ModelBinding
import kotlin.math.pow

class MG1ModelFragment : Fragment() {

    private var _binding : FragmentMG1ModelBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMG1ModelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val args: MG1ModelFragmentArgs by navArgs()

        val lambda = args.lambda.toDouble()
        val mu = args.mu.toDouble()
        val n = args.n.toInt()
        val sigma = args.sigma.toDouble()
        val cs = args.cs.toDouble()
        val cw = args.cw.toDouble()
        val lq : Double

        Log.i("lamda", args.lambda)
        Log.i("mu", args.mu)
        Log.i("n", args.n)
        Log.i("sigma", args.sigma)
        Log.i("cs", args.cs)
        Log.i("cw", args.cw)

        val p = lambda/mu
        binding.tvmg1rho.text = p.toString()

        val p0 = 1 - p
        binding.tvmg1p0.text = p0.toString()

        val pn = p0 * p.pow(n)
        binding.tvmg1pn.text = pn.toString()

        if(sigma == 0.0){
            binding.tvModelo.text = "Resultados M/D/1"
            lq = lqMD1(p)
            binding.tvmg1lq.text = lq.toString()

        }else {
            lq = lqMG1(lambda, sigma, p)
            binding.tvmg1lq.text = lq.toString()
        }

        val l = l(lq, p)
        binding.tvmg1l.text = l.toString()

        val wq = wq(lq, lambda)
        binding.tvmg1wq.text = wq.toString()

        val w = w(wq, mu)
        binding.tvmg1w.text = w.toString()

        val ct = ct(lq, cs, cw)
        binding.tvmg1ct.text = ct.toString()


    }

    private fun lqMG1(lambda : Double, sigma : Double, p : Double) : Double {
        return ((lambda.pow(2) * sigma.pow(2)) + p.pow(2)) / (2 * (1 - p))
    }

    private fun lqMD1(p : Double) : Double {
        return p.pow(2) / (2 *  (1 -  p))
    }

    private fun l(lq : Double, p : Double) : Double {
        return p + lq
    }

    private fun wq(lq : Double, lambda: Double) : Double {
        return lq/lambda
    }

    private fun w(wq : Double, mu : Double) : Double {
        return  wq + (1/mu)
    }

    private fun ct(lq: Double, cs: Double, cw : Double) : Double {
        return (lq * cw) + cs
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
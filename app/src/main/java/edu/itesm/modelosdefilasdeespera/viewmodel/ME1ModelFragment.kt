package edu.itesm.modelosdefilasdeespera.viewmodel

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import edu.itesm.modelosdefilasdeespera.R
import edu.itesm.modelosdefilasdeespera.databinding.FragmentME1ModelBinding
import kotlin.math.pow

class ME1ModelFragment : Fragment() {

    private var _binding : FragmentME1ModelBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentME1ModelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val args : ME1ModelFragmentArgs by navArgs()

        val lambda = args.lambda.toDouble()
        val mu = args.mu.toDouble()
        val n = args.n.toInt()
        val k = args.k.toDouble()
        val cs = args.cs.toDouble()
        val cw = args.cw.toDouble()

        Log.i("lamda", args.lambda)
        Log.i("mu", args.mu)
        Log.i("n", args.n)
        Log.i("k", args.k)
        Log.i("cs", args.cs)
        Log.i("cw", args.cw)

        val p = lambda/mu
        binding.tvme1rho.text = p.toString()

        val p0 = 1 - p
        binding.tvme1p0.text = p0.toString()

        val pn = p0 * p.pow(n)
        binding.tvme1pn.text = pn.toString()

        val lq = lq(lambda, mu, k)
        binding.tvme1lq.text = lq.toString()

        val wq = wq(lq, lambda)
        binding.tvme1wq.text = wq.toString()

        val w = w(wq, mu)
        binding.tvme1w.text = w.toString()

        val l = l(lambda, w)
        binding.tvme1l.text = l.toString()

        val ct = ct(lq, cs, cw)
        binding.tvme1ct.text = ct.toString()
    }

    private fun lq(lambda: Double, mu: Double, k : Double) : Double {
        Log.i("k", k.toString())
        val aux1 = (k + 1) / (k * 2)
        val aux2 = lambda.pow(2) / (mu * ( mu - lambda))
        Log.i("Aux1", aux1.toString())
        Log.i("Aux2", aux2.toString())
        return aux1 * aux2
    }

    private fun l(lambda: Double , w : Double) : Double {
        return lambda * w
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
package edu.itesm.modelosdefilasdeespera.viewmodel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import edu.itesm.modelosdefilasdeespera.databinding.FragmentMMSModelBinding
import kotlin.math.pow

class MMSModelFragment : Fragment() {

    private var _binding : FragmentMMSModelBinding?  = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMMSModelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val args : MMSModelFragmentArgs by navArgs()

        val lambda = args.lambda.toDouble()
        val mu = args.mu.toDouble()
        val n = args.n.toInt()
        val s = args.s.toInt()
        val cs = args.cs.toDouble()
        val cw = args.cw.toDouble()

        val p = lambda/(s * mu)
        binding.tvmmsrho.text = p.toString()

        val p0 = p0(s, lambda, mu, p)
        binding.tvmmsp0.text = p0.toString()

        val pn = pn(lambda, mu, s, n, p0)
        binding.tvmmspn.text = pn.toString()

        val lq = lq(p0, lambda, mu, p, s)
        binding.tvmmslq.text = lq.toString()

        val l = l(lq, lambda, mu)
        binding.tvmmsl.text = l.toString()

        val wq = wq(lq, lambda)
        binding.tvmmswq.text = wq.toString()

        val w = w(wq, mu)
        binding.tvmmsw.text = w.toString()

        val ct = ct(lq, cs, cw, s)
        binding.tvmmsct.text = ct.toString()

    }

    private  fun p0( s: Int, lambda: Double, mu: Double, p: Double) : Double {
        var sum = 0.0
        var aux : Double
        var p0: Double

        for(n in 0..s-1){
            aux = (lambda/mu).pow(n)/factorial(n)
            sum += aux
        }

        var aux2 = ((lambda/mu).pow(s) / factorial(s)) * (1 / (1 - p))

        p0 = (sum + aux2).pow(-1)

        return p0
    }

    private fun pn(lambda: Double, mu: Double, s: Int, n: Int, p0: Double): Double {

        if(n <= 0 && n < s) {
            return ((lambda / mu).pow(n) / factorial(n)) * p0

        }else if(n >= s){

            return ((lambda / mu).pow(n) / (factorial(s) * s.toDouble().pow(n - s))) * p0
        }
        return 0.0
    }

    private fun factorial(num: Int): Long {
        if (num >= 1)
            return num * factorial(num - 1)
        else
            return 1
    }

    private fun lq(p0 : Double, lambda: Double, mu: Double, p : Double , s : Int) : Double {
        return (p0 * (lambda/mu).pow(s) * p) / (factorial(s) * (1 - p).pow(2))
    }

    private fun l(lq : Double, lambda: Double, mu : Double) : Double {
        return lq + (lambda/mu)
    }

    private fun wq(lq : Double, lambda: Double) : Double {
        return lq/lambda
    }

    private fun w(wq : Double, mu: Double) : Double {
        return  wq + (1/mu)
    }

    private fun ct(lq: Double, cs: Double, cw : Double, s: Int) : Double {
        return (lq * cw) + (s * cs)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
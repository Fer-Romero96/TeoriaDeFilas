package edu.itesm.modelosdefilasdeespera.viewmodel

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.navArgs
import edu.itesm.modelosdefilasdeespera.databinding.FragmentMMsKModelBinding
import kotlin.math.pow

class MMsKModelFragment : Fragment() {

    private var _binding : FragmentMMsKModelBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMMsKModelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val args : MMsKModelFragmentArgs by navArgs()

        val lambda = args.lambda.toDouble()
        val mu = args.mu.toDouble()
        val n = args.n.toInt()
        val s = args.s.toInt()
        val k = args.k.toInt()
        val cs = args.cs.toDouble()
        val cw = args.cw.toDouble()
        var lambdaEfectiva : Double

        Log.i("lambda", lambda.toString())
        Log.i("mu", mu.toString())
        Log.i("n", n.toString())
        Log.i("s", s.toString())
        Log.i("k", k.toString())
        Log.i("cs", cs.toString())
        Log.i("cw", cw.toString())

        if(n != k){
            alertas("Lambda NO Efectiva","Se recomienda que n sea igual a k para conseguir la " +
                    "tasa efectiva de arribo")
        }

        val p = lambda/(s * mu)
        binding.tvmmskrho.text = p.toString()

        val p0 = p0(s, lambda, mu, p, k)
        binding.tvmmskp0.text = p0.toString()

        val pn = pn(lambda, mu, s, n, p0, k)
        binding.tvmmskpn.text = pn.toString()

        lambdaEfectiva = lambda

        if(n == k){
            lambdaEfectiva = lambda * (1 - pn)
            binding.lambdaefectivammsk.text = lambdaEfectiva.toString()
            binding.linLayEfectiva.visibility = View.VISIBLE

        }

        val lq = lq(p0, lambda, mu, p, s, k)
        binding.tvmmsklq.text = lq.toString()

        val wq = wq(lq, lambdaEfectiva)
        binding.tvmmskwq.text = wq.toString()

        val w = w(wq, mu)
        binding.tvmmskw.text = w.toString()

        val l = l(lambdaEfectiva, w)
        binding.tvmmskl.text = l.toString()

        val ct = ct(lq, cs, cw, s)
        binding.tvmmskct.text = ct.toString()

    }

    private  fun p0( s: Int, lambda: Double, mu: Double, p: Double, k: Int) : Double {
        var sum1 = 0.0
        var sum2 = 0.0
        var aux1 : Double
        var aux2 : Double
        var p0: Double

        for(n in 0..s){
            aux1 = (lambda/mu).pow(n)/factorial(n)
            sum1 += aux1
        }

        for(n in (s+1)..k){
            aux2 = (p).pow(n - s)
            sum2 += aux2
        }

        var aux3 =  ((lambda/mu).pow(s) / factorial(s)) * sum2

        p0 = (sum1 + aux3).pow(-1)

        return p0
    }

    private fun pn(lambda: Double, mu: Double, s: Int, n: Int, p0: Double, k: Int): Double {

        if(n <= s) {
            if(n > k){
                return 0.0
            }
            return ((lambda / mu).pow(n) / factorial(n)) * p0

        }else if(s <= k){
            if(n > k){
                return 0.0
            }
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

    private fun lq(p0: Double, lambda: Double, mu: Double, p: Double, s: Int, k: Int): Double {

        val aux1 = (p0 * (lambda / mu).pow(s) * p) / (factorial(s) * (1 - p).pow(2))
        val aux2 = 1 - p.pow(k - s) - ((k - s) * p.pow(k - s) * (1 - p))

        return aux1 * aux2
    }

    private fun l(lambdaEfectiva : Double, w : Double) : Double {
        return lambdaEfectiva * w
    }

    private fun wq(lq : Double, lambdaEfectiva: Double) : Double {
        return lq/lambdaEfectiva
    }

    private fun w(wq : Double, mu: Double) : Double {
        return  wq + (1/mu)
    }

    private fun ct(lq: Double, cs: Double, cw : Double, s: Int) : Double {
        return (lq * cw) + (s * cs)
    }

    private fun alertas(titulo: String , mensaje : String){
        val builder = AlertDialog.Builder(requireActivity())
        with(builder){
            setTitle(titulo)
            setMessage(mensaje)
            setPositiveButton("Ok",null)
            show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding =  null
    }
}
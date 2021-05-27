package edu.itesm.modelosdefilasdeespera.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.findNavController
import edu.itesm.modelosdefilasdeespera.databinding.FragmentMMsKMenuBinding

class MMsKMenuFragment : Fragment() {

    private var _binding : FragmentMMsKMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMMsKMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val lambda = binding.TasaLlegadammsk
        val mu = binding.TasaServiciommsk
        val n = binding.Clientesmmsk
        val s = binding.NumeroCanalesmmsk
        val k = binding.LimiteSistemammsk
        val cs = binding.CostoServiciommsk
        val cw = binding.CostoTiempoEsperammsk

        binding.calcularmmsk.setOnClickListener {

            if(lambda.length() != 0 && mu.length() != 0 && cs.length() != 0 && cw.length() != 0
                && s.length() != 0 && k.length() != 0 && n.length() != 0){

                val p = lambda.text.toString().toDouble()/(mu.text.toString().toDouble()*s.text.toString().toDouble())

                if(p < 1){
                    val action = MMsKMenuFragmentDirections.actionMMsKMenuFragmentToMMsKModelFragment(lambda.text.toString(),
                        mu.text.toString(),
                        n.text.toString(),
                        s.text.toString(),
                        k.text.toString(),
                        cs.text.toString(),
                        cw.text.toString())
                    view?.findNavController()?.navigate(action)

                }else{
                    alertas("Sistema NO Estable","El sistema no es estable debido a que Rho no es" +
                            " menor a uno | Rho Actual: $p")
                }

            }else{
                alertas("Campos Faltantes","Falta completar algunos de los campos anteriores")
            }
        }
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
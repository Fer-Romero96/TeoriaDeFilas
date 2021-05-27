package edu.itesm.modelosdefilasdeespera.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.findNavController
import edu.itesm.modelosdefilasdeespera.R
import edu.itesm.modelosdefilasdeespera.databinding.FragmentMG1MenuBinding

class MG1MenuFragment : Fragment() {

    private var _binding : FragmentMG1MenuBinding? = null
    private val binding get() =  _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMG1MenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val lambda = binding.TasaLlegadamg1
        val mu = binding.TasaServiciomg1
        val n = binding.Clientesmg1
        val sigma = binding.DesviacionEstandarmg1
        val cs = binding.CostoServiciomg1
        val cw = binding.CostoTiempoEsperamg1

        binding.calcularmg1.setOnClickListener {

            if(lambda.length() != 0 && mu.length() != 0 && cs.length() != 0 && cw.length() != 0
                && n.length() != 0 && sigma.length() != 0){

                val p = lambda.text.toString().toDouble()/mu.text.toString().toDouble()

                if( p < 1){
                    val action = MG1MenuFragmentDirections.actionMG1MenuFragmentToMG1ModelFragment(lambda.text.toString(),
                        mu.text.toString(),
                        n.text.toString(),
                        sigma.text.toString(),
                        cs.text.toString(),
                        cw.text.toString())
                    view?.findNavController()?.navigate(action)
                }else{
                    alertas("Sistema NO Estable","El sistema no es estable debido a que Rho no es" +
                            " menor a uno | Rho Actual: $p")
                }

            } else {
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
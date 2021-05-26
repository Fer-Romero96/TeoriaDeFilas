package edu.itesm.modelosdefilasdeespera.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.findNavController
import edu.itesm.modelosdefilasdeespera.R
import edu.itesm.modelosdefilasdeespera.databinding.FragmentME1MenuBinding

class ME1MenuFragment : Fragment() {

    private var _binding : FragmentME1MenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentME1MenuBinding.inflate(inflater, container, false)
        return  binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val lambda = binding.TasaLlegadamk1
        val mu = binding.TasaServiciomk1
        val n = binding.Clientesmk1
        val k = binding.Ordenmk1
        val cs = binding.CostoServiciomk1
        val cw = binding.CostoTiempoEsperamk1

        binding.calcularmk1.setOnClickListener {

            if(lambda.length() != 0 && mu.length() != 0 && cs.length() != 0 && cw.length() != 0
                && k.length() != 0 && n.length() != 0){

                val p = lambda.text.toString().toDouble()/mu.text.toString().toDouble()

                if(p < 1){

                    val action = ME1MenuFragmentDirections.actionME1FragmentToME1ModelFragment(lambda.text.toString(),
                        mu.text.toString(),
                        n.text.toString(),
                        k.text.toString(),
                        cs.text.toString(),
                        cw.text.toString())
                    view?.findNavController()?.navigate(action)

                }else {
                    alertas("No Estable","El sistema no es estable p < 1")
                }

            } else{
                alertas("Campos Vacios","Faltan Llenar Algunos de Los Campos Especificados")
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
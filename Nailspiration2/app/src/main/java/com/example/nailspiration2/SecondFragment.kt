package com.example.nailspiration2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.nailspiration2.databinding.FragmentSecondBinding
import java.nio.file.Files.size
import com.example.nailspiration2.Polish as Polish1

data class Polish(
    var polishName: String,
    var companyName: String,
    var polishType: String,
    var polishEffect: String
)

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {
    private var _binding: FragmentSecondBinding? = null
    val typeOfPolish = arrayOf("Regular", "Stamping", "Gel")
    val polishEffect = arrayOf("Creme", "Holo Glitter", "Linear Holo", "Scattered Holo", "Magnetic", "Thermal", "Solar", "Metallic", "Multichrome")
    lateinit var polishName: String
    lateinit var polishCompany: String
    lateinit var polishTypeSelection: String
    lateinit var polishEffectSelection: String
    //var polishes = mutableListOf<Polish>(Polish(polishName, polishCompany, polishTypeSelection, polishEffectSelection))



    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        val arrayAdapterType = ArrayAdapter(requireContext(), R.layout.dropdown_item, typeOfPolish)
        binding.autoCompleteTextView.setAdapter(arrayAdapterType)

        val arrayAdapterEffect = ArrayAdapter(requireContext(), R.layout.dropdown_item, polishEffect)
        binding.autoCompleteTextViewEffect.setAdapter(arrayAdapterEffect)

        binding.autoCompleteTextView.doAfterTextChanged {
           polishTypeSelection = binding.autoCompleteTextView.text.toString()
        }
        binding.autoCompleteTextViewEffect.doAfterTextChanged {
            polishEffectSelection = binding.autoCompleteTextViewEffect.text.toString()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addPolishButton.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
            polishName = binding.polishNameEditText.text.toString()
            polishCompany = binding.companyNameEditText.text.toString()
            Polish1(polishName, polishCompany, polishTypeSelection, polishEffectSelection)

        }
    }

    private fun onAddPolish() {
        val polishName = binding.polishNameEditText.text.toString()
        val polishCompany = binding.companyNameEditText.text.toString()
        val polishType = binding.autoCompleteTextView.text.toString()
        val polishEffect = binding.autoCompleteTextViewEffect.text.toString()
        Polish1(polishName, polishCompany, polishType, polishEffect)

    }


    /*
    private fun printPolishData() {

        println("Number of polishes: ${polishes.size}")
        println("Name of polish: ${polishes[1].polishName}")
    }*/

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

/*
class Polish {

}
    var polishName: String,
    var companyName: String,
    var polishType: String,
    var polishEffect: String


fun main(args: Array<String>) {
    val polishes = mutableListOf(Polish("Queens Cures", "Holo Taco", "Regular", "Magentic"),
    Polish("Twice In A Blue Moon", "Holo Taco", "Regular", "Magentic"),
    Polish("Test", "Holo Taco", "Regular", "Magentic"))

    println("Number of polishes: ${polishes.size}")
    println("Name of polish: ${polishes[1].polishName}")
}
 */

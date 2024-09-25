package com.pp.personalmangement.ui.petrol_cal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.pp.personalmangement.R

class PetrolCalFragment : Fragment() {

    companion object {
        fun newInstance() = PetrolCalFragment()
    }

    private lateinit var viewModel: PetrolCalViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PetrolCalViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_petrol_cal, container, false)

        val mileage = view.findViewById<TextInputEditText>(R.id.mileage)
        val petrolPrice = view.findViewById<TextInputEditText>(R.id.petrolPrice)
        val kmDriven = view.findViewById<TextInputEditText>(R.id.kmDriven)
        val amount = view.findViewById<TextInputEditText>(R.id.amount)
        val calculate = view.findViewById<Button>(R.id.calculate)

        val petolCard = view.findViewById<CardView>(R.id.petroCard);

        val startDistance = view.findViewById<TextInputEditText>(R.id.startDistance)
        val endDistance = view.findViewById<TextInputEditText>(R.id.endDistance)

        val distCalculate = view.findViewById<Button>(R.id.distCalculate)

        distCalculate.setOnClickListener {


            if (startDistance.text.toString() != "" && endDistance.text.toString() != "") {

                if (endDistance.text.toString().toDouble() > startDistance.text.toString()
                        .toDouble()) {

                    val startDistanceValue: Double = startDistance.text.toString().toDouble()
                    val endDistanceValue: Double = endDistance.text.toString().toDouble()

                    val totalDistance: Double = endDistanceValue - startDistanceValue
                    val formattedDistanceAmt = String.format("%.2f", totalDistance)

                    kmDriven.setText(formattedDistanceAmt)

                } else {
                    Toast.makeText(context, "End KM Cannot be Less than Start KM", Toast.LENGTH_SHORT).show()
                }

            } else {
                Toast.makeText(context, "Enter KM Driven", Toast.LENGTH_SHORT).show()
            }


        }


        fun onCheckboxClick(view: View) {


        }

        calculate.setOnClickListener {

            if (mileage.text.toString() == "") {
                // Use the mileage value (integer)
                Toast.makeText(context, "Enter Mileage. Please enter a number.", Toast.LENGTH_SHORT)
                    .show()
            } else if (petrolPrice.text.toString() == "") {

                Toast.makeText(
                    context,
                    "Enter Petrol Value. Please enter a number.",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (kmDriven.text.toString() == "") {

                Toast.makeText(
                    context,
                    "Enter KM Value. Please enter a number.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {

                val mileageValue: Double = mileage.text.toString().toDouble()
                val petrolValue: Double = petrolPrice.text.toString().toDouble();
                val kmDrivenValue: Double = kmDriven.text.toString().toDouble();

                val petrolAmt: Double = (petrolValue / mileageValue) * kmDrivenValue
                val formattedPetrolAmt = String.format("%.2f", petrolAmt)
                amount.setText(formattedPetrolAmt)
                petolCard.setVisibility(View.VISIBLE)

            }


        }







        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }
}
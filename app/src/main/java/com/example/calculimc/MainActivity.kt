package com.example.calculimc

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var editTextPoids: EditText
    private lateinit var editTextTaille: EditText
    private lateinit var buttonCalculer: Button
    private lateinit var buttonEffacer: Button
    private lateinit var textViewImc: TextView
    private lateinit var textViewCategorie: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        editTextPoids = findViewById(R.id.editTextPoids)
        editTextTaille = findViewById(R.id.editTextTaille)
        buttonCalculer = findViewById(R.id.buttonCalculer)
        buttonEffacer = findViewById(R.id.buttonEffacer)
        textViewImc = findViewById(R.id.textViewImc)
        textViewCategorie = findViewById(R.id.textViewCategorie)

        buttonCalculer.setOnClickListener {
            calculerIMC()
        }

        buttonEffacer.setOnClickListener {
            effacer()
        }
    }

    private fun calculerIMC() {

        val poidsTexte = editTextPoids.text.toString().trim()
        val tailleTexte = editTextTaille.text.toString().trim()

        if (poidsTexte.isEmpty() || tailleTexte.isEmpty()) {

            Toast.makeText(
                this,
                getString(R.string.erreur_champs_vides),
                Toast.LENGTH_SHORT
            ).show()

            return
        }

        val poids = poidsTexte.toDoubleOrNull()
        val taille = tailleTexte.toDoubleOrNull()

        if (poids == null || taille == null) {

            Toast.makeText(
                this,
                getString(R.string.erreur_valeurs),
                Toast.LENGTH_SHORT
            ).show()

            return
        }

        if (poids <= 0 || taille <= 0) {

            Toast.makeText(
                this,
                getString(R.string.erreur_valeurs),
                Toast.LENGTH_SHORT
            ).show()

            return
        }

        val imc = poids / (taille * taille)

        textViewImc.text = String.format(
            Locale.getDefault(),
            getString(R.string.format_imc),
            imc
        )

        val categorie: String
        val couleur: Int

        if (imc < 18.5) {

            categorie = getString(R.string.insuffisance)
            couleur = Color.rgb(255, 152, 0)

        } else if (imc < 25) {

            categorie = getString(R.string.normale)
            couleur = Color.rgb(0, 128, 0)

        } else if (imc < 30) {

            categorie = getString(R.string.surpoids)
            couleur = Color.rgb(255, 152, 0)

        } else if (imc < 35) {

            categorie = getString(R.string.obesite_moderee)
            couleur = Color.RED

        } else if (imc < 40) {

            categorie = getString(R.string.obesite_severe)
            couleur = Color.RED

        } else {

            categorie = getString(R.string.obesite_morbide)
            couleur = Color.rgb(139, 0, 0)
        }

        textViewCategorie.text = getString(
            R.string.format_categorie,
            categorie
        )

        textViewCategorie.setTextColor(couleur)
        textViewImc.setTextColor(couleur)
    }

    private fun effacer() {

        editTextPoids.text.clear()
        editTextTaille.text.clear()

        textViewImc.text = getString(R.string.imc_initial)
        textViewCategorie.text = getString(R.string.categorie_initiale)

        textViewImc.setTextColor(Color.BLACK)
        textViewCategorie.setTextColor(Color.BLACK)

        editTextPoids.requestFocus()
    }
}
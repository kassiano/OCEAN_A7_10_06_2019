package br.com.ocean_a7_10_06_2019

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var databaseManager : DatabaseManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        databaseManager = DatabaseManager(DatabaseHelper(this))

        btSalvar.setOnClickListener {

            val nome = editNome.text.toString()
            val telefone = editTelefone.text.toString()

            salvarContato(nome, telefone)
        }

        selecionarContatos()
    }

    fun salvarContato(nome:String, telefone:String){

        databaseManager.inserirContato(nome, telefone)
        selecionarContatos()
    }

    fun selecionarContatos(){
        val contatos = databaseManager.selecionarContatos()

        linearContatos.removeAllViews()
        contatos.forEach {

            val textView = TextView(this)
            textView.text = it.nome

            textView.setOnClickListener { v->
                Toast.makeText(this, "Telefone: ${it.telefone}",
                    Toast.LENGTH_SHORT).show()
            }


            linearContatos.addView(textView)
            //Toast.makeText(this, it.nome, Toast.LENGTH_SHORT).show()
        }
    }

}

package br.com.ocean_a7_10_06_2019

import android.content.ContentValues

class DatabaseManager(val dbHelper: DatabaseHelper) {


    fun inserirContato(nome:String, telefone:String): Long {

        val db = dbHelper.writableDatabase

        val valores = ContentValues().apply {
            put("nome", nome)
            put("telefone", telefone)
        }

        val id = db.insert("contatos", null, valores)

        return id
    }

    fun selecionarContatos(): List<Contato>{

        val db = dbHelper.readableDatabase

        val sql = "SELECT * FROM contatos"

        val cursor = db.rawQuery(sql, null)

        val resultado = mutableListOf<Contato>()

        if(cursor.moveToFirst()){

            do {
                val nomeDb = cursor.getString(cursor.getColumnIndex("nome"))
                val telefoneDb = cursor.getString(cursor.getColumnIndex("telefone"))

                resultado.add(Contato(nomeDb, telefoneDb))

            }while (cursor.moveToNext())

            cursor.close()
        }

        return resultado
    }


}
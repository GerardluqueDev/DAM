package com.example.usuarios.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.usuarios.database.DBHelper
import com.example.usuarios.model.User

class UserDAO(var contexto: Context){

    //guardamos todas las acciones contra BBDD del modelo User

    // Create
    fun insertUser(user: User){
        val dataBase: SQLiteDatabase = DBHelper(contexto,"users_db",null,1).writableDatabase //modo escritura
        val content = ContentValues()
        content.put("name",user.name)
        content.put("age",user.age)
        dataBase.insert("users",null,content)
    }

    //Select
    fun getUsers(): Int{
        val dataBase: SQLiteDatabase = DBHelper(contexto,"users_db",null,1).readableDatabase //modo lectura

        var contador:Int = 0

        val resultado: Cursor = dataBase.query(
            "users", arrayOf("name", "age"),
            null,
            null,
            null,
            null,
            null
        )

        //Hay que recorrer el cursor: mientras haya siguiente, dame el resultado
        while (resultado.moveToNext()){
            contador++
            val name = resultado.getString(resultado.getColumnIndexOrThrow("name"))
            val edad = resultado.getInt(resultado.getColumnIndexOrThrow("age"))
            Log.v("database_users","Name: ${name} Age: $edad")
        }
        return contador
    }

}
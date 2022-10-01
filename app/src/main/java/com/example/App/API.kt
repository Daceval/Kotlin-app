package com.example.App

class API {

    companion object {
        fun login(usuario: String, contrasenia: String, callback: (ok:Boolean )-> Unit) {
            if (usuario == "admin" && contrasenia == "12345"){
                callback(true)
            }else {
                callback(false)
            }

        }
    }
}
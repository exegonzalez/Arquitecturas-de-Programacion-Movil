package com.example.tp_final_mobile.view.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.tp_final_mobile.R
import com.example.tp_final_mobile.model.BeeUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setActionBar(findViewById(R.id.toolbarMain))

        configNav()

        /*val jsonArr = JSONArray("[\n" +
                "            {\n" +
                "                'name' : 'Apícola Romi',\n" +
                "                'email' : 'ApicolaRomi@gmail.com',\n" +
                "                'image' : 'https://i.ibb.co/LYWLCST/apicultor-1.png',\n" +
                "                'jobtitle' : 'Apicultor',\n" +
                "                'biography' : 'Empresa que se encarga de la comercialización de productos elaborados a partir de miel de abeja. Ruta 135, Km 7. Colón, Entre Ríos, Argentina.',\n" +
                "                'phone' : '543447448078',\n" +
                "                'web' : 'https://twitter.com/apicolaromi?lang=es',\n" +
                "                'address' : 'Colón, Entre Ríos, Argentina.',\n" +
                "                'latitude' : -32.2109955,\n" +
                "                'longitude' : -58.2105534\n" +
                "            },\n" +
                "            {\n" +
                "                'name' : 'Apícola Danangie',\n" +
                "                'email' : 'danangie@apicola-danangie.com.ar',\n" +
                "                'image' : 'https://i.ibb.co/LYWLCST/apicultor-1.png',\n" +
                "                'jobtitle' : 'Apicultor',\n" +
                "                'biography' : 'Apícola Danangie es una empresa familiar dedicada a la actividad apícola y forestal. Comenzamos en 1991 con solamente 400 colmenas produciendo miel y polen monoflor de eucaliptos. Concordia, Entre Ríos, Argentina.',\n" +
                "                'phone' : '543454217315',\n" +
                "                'web' : 'http://www.apicola-danangie.com.ar/es/',\n" +
                "                'address' : 'Concordia, Entre Ríos, Argentina.',\n" +
                "                'latitude' : -31.6951881,\n" +
                "                'longitude' : -58.9570678\n" +
                "            },\n" +
                "            {\n" +
                "                'name' : 'COSAR Cooperativa Apícola',\n" +
                "                'email' : 'info@coopcosar.com',\n" +
                "                'image' : 'https://i.ibb.co/4F0Lsnf/apicultor-4.png',\n" +
                "                'jobtitle' : 'Cooperativa apícola',\n" +
                "                'biography' : 'COSAR es una cooperativa integrada por 120 pequeños y medianos apicultores argentinos que viven y trabajan en la zona central de la provincia de Santa Fe (Argentina).',\n" +
                "                'phone' : '543564481206',\n" +
                "                'web' : 'https://coopcosar.com/',\n" +
                "                'address' : 'Sauce Viejo, Santa Fe, Argentina.',\n" +
                "                'latitude' : -31.8588882,\n" +
                "                'longitude' : -60.794946\n" +
                "            },\n" +
                "            {\n" +
                "                'name' : 'Apícola Saley',\n" +
                "                'email' : 'ApicolaSaley@gmail.com',\n" +
                "                'image' : 'https://i.ibb.co/LYWLCST/apicultor-1.png',\n" +
                "                'jobtitle' : 'Apicultor',\n" +
                "                'biography' : 'Empresa que se dedica a la venta de miel pura de abeja fraccionada o a granel cosechada de la zona de entre rios y zonas de isla del delta del parana. San Nicolás de Los Arroyos, Buenos Aires.',\n" +
                "                'phone' : '5493364211731',\n" +
                "                'web' : 'https://www.facebook.com/apicola.saley',\n" +
                "                'address' : 'San Nicolás de Los Arroyos, Buenos Aires, Argentina.',\n" +
                "                'latitude' : -33.3505562,\n" +
                "                'longitude' : -60.1415042\n" +
                "            },\n" +
                "            {\n" +
                "                'name' : 'Apiarios Fanoni',\n" +
                "                'email' : 'apiariosfanoni@hotmail.com',\n" +
                "                'image' : 'https://i.ibb.co/2cqPfNn/apicultor-2.png',\n" +
                "                'jobtitle' : 'Insumos apícolas',\n" +
                "                'biography' : 'Recuperadora y estampadora de cera San Miguel. Venta y canje de cera estampada para apicultura. Comercialización de insumos varios para el apicultor. Villa Elisa, Entre Ríos, Argentina.',\n" +
                "                'phone' : '543447513246',\n" +
                "                'web' : 'https://www.facebook.com/apiarios.fanoni',\n" +
                "                'address' : 'Villa Elisa, Entre Ríos, Argentina.',\n" +
                "                'latitude' : -33.3505562,\n" +
                "                'longitude' : -60.1415042\n" +
                "            },\n" +
                "            {\n" +
                "                'name' : 'Apicutodo',\n" +
                "                'email' : 'apicutodo@yahoo.com.ar',\n" +
                "                'image' : 'https://i.ibb.co/2cqPfNn/apicultor-2.png',\n" +
                "                'jobtitle' : 'Insumos apícolas',\n" +
                "                'biography' : 'Somos una empresa de apicultura, cosmetica, regaleria, productos naturales con todos los insumos y maquinarias para los apicultores. Buenos Aires, Argentina.',\n" +
                "                'phone' : '01148617020',\n" +
                "                'web' : 'https://www.apicultodo.com/',\n" +
                "                'address' : 'Luis Guillón, Buenos Aires, Argentina.',\n" +
                "                'latitude' : -34.6132364,\n" +
                "                'longitude' : -58.4493912\n" +
                "            },\n" +
                "            {\n" +
                "                'name' : 'Mundo Abeja Apicultura',\n" +
                "                'email' : 'mundoabejaoeste@gmail.com',\n" +
                "                'image' : 'https://i.ibb.co/4F0Lsnf/apicultor-4.png',\n" +
                "                'jobtitle' : 'Cooperativa apícola',\n" +
                "                'biography' : 'Mundo Abeja es un emprendimiento familiar apícola asociado a productores locales. Se produce miel, propoleos, cera virgen y mucho mas. Paso del Rey, Buenos Aires.',\n" +
                "                'phone' : '01155078108',\n" +
                "                'web' : 'https://www.facebook.com/mundoabejacasaapicola/',\n" +
                "                'address' : 'Paso del Rey, Buenos Aires, Argentina.',\n" +
                "                'latitude' : -34.4813143,\n" +
                "                'longitude' : -58.859495\n" +
                "            },\n" +
                "            {\n" +
                "                'name' : 'Apicultura Colmenas',\n" +
                "                'email' : 'ApiculturaColmenas@gmail.com',\n" +
                "                'image' : 'https://i.ibb.co/2cqPfNn/apicultor-2.png',\n" +
                "                'jobtitle' : 'Insumos apícolas',\n" +
                "                'biography' : 'Empresa que se dedica a la venta de materiales e insumos apícolas. Paraná, Entre Ríos, Argentina.',\n" +
                "                'phone' : '343155089057',\n" +
                "                'web' : 'https://www.facebook.com/apiculturacolmenas/',\n" +
                "                'address' : 'Paraná, Entre Ríos, Argentina.',\n" +
                "                'latitude' : -32.2488134,\n" +
                "                'longitude' : -59.9315472\n" +
                "            },\n" +
                "        ]")


        val firebaseFirestore = FirebaseFirestore.getInstance()

        for (i in 0 until jsonArr.length()-1) {
            val aux = jsonArr.get(i) as JSONObject
            val beeuser = BeeUser()

            beeuser.name = aux.getString("name")
            beeuser.email = aux.getString("email")
            beeuser.image = aux.getString("image")
            beeuser.jobtitle = aux.getString("jobtitle")
            beeuser.biography = aux.getString("biography")
            beeuser.phone = aux.getString("phone")
            beeuser.web = aux.getString("web")
            beeuser.address = aux.getString("address")
            beeuser.latitude = aux.getDouble("latitude")
            beeuser.longitude = aux.getDouble("longitude")

            firebaseFirestore.collection("beeusers").document().set(beeuser)
        }*/


    }

    private fun configNav() {
        NavigationUI.setupWithNavController(
            bnvMenu, Navigation.findNavController(
                this,
                R.id.fragContent
            )
        )
    }

}
package com.baqynra.withbaqyand.kewarganegaraan.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.baqynra.withbaqyand.kewarganegaraan.Preferences
import com.baqynra.withbaqyand.kewarganegaraan.R
import com.baqynra.withbaqyand.kewarganegaraan.api.ApiClient
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    companion object{
        private lateinit var myview: View
        private var preferences  = Preferences()
        var name : String? = null
        private var alias : String? = null
        private var tglLahir : String? = null
        private var noTelp : String? = null
        private var photo : String? = null
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        preferences.setPreferences(this)
        initData()
        nama.text = name
        no_telp.text = noTelp
        tgl_lahir.text = tglLahir



    }

    fun initData(){
       val apiservice = ApiClient().getApiService(this)
        apiservice?.profile_warga()?.enqueue(object : Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if(response.isSuccessful){
                    if (response.body() != null){
                        try {
                                val obj = JSONObject(response.body()!!.string())
                                val data = JSONArray(obj.optString("user"))
                                for (counter in 0 until data.length()){
                                    val jsonObject = data.getJSONObject(counter)
                                    name = jsonObject.optString("nama")
                                    noTelp = jsonObject.optString("no_hp")
                                    alias = jsonObject.optString("alias")
                                    tglLahir = jsonObject.optString("tgl_lahir")
                                    photo = jsonObject.optString("foto")
                                }
                            nama.text = name
                            no_telp.text = noTelp
                            tgl_lahir.text = tglLahir

                        }catch (e : Exception){
                            e.printStackTrace()

                            Toast.makeText(this@MainActivity, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        Toast.makeText(this@MainActivity, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }

            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Koneksi Bermasalah", Toast.LENGTH_SHORT).show()
            }
        })

        }


    }

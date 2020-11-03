package net.bedev.testlumut

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import net.bedev.testlumut.adapter.LumutAdapter
import net.bedev.testlumut.help.See
import net.bedev.testlumut.model.modelLumut
import net.bedev.testlumut.rest.ApiConfig
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RvGetTodos.layoutManager = LinearLayoutManager(applicationContext)
        val apiService = ApiConfig.getApiService()
        apiService.get_todos()
            .enqueue(object : Callback<ResponseBody>{
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                 val respon = response.body()?.string()
                    See.log("Response todos: $respon")
                    val json = JSONArray(respon)
                    if (json != null){
                        val data = Gson().fromJson(respon, modelLumut::class.java)
                        val adapterView = LumutAdapter(ArrayList(data))
                        RvGetTodos.adapter = adapterView

                    }else {
                        See.toast(this@MainActivity,"Data Kosong")
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    See.toast(this@MainActivity,"Cek koneksi internet")
                }

            })

    }
}
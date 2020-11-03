package net.bedev.testlumut

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_main.*
import net.bedev.testlumut.adapter.LumutAdapter
import net.bedev.testlumut.help.See
import net.bedev.testlumut.model.modelLumut
import net.bedev.testlumut.rest.ApiConfig
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
       val id = intent.getIntExtra("id",0)
        See.log(id.toString())


        val apiService = ApiConfig.getApiService()
        if (id != null) {
            apiService.post_todos(id)
                .enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                    ) {
                        val respon = response.body()?.string()
                        See.log("Response todos: $respon")
                        val json = JSONObject(respon)
                        if (json != null){
                            val data = Gson().fromJson(respon, modelLumut.modelLumutItem::class.java)
                            TvcompletedDetail.text = data.completed.toString()
                            TvtitleDetail.text = data.title
                            TvuserIdDetail.text = data.userId.toString()
                            TvidDetail.text = data.id.toString()

                        }else {
                            See.toast(this@DetailActivity,"Data Kosong")
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        See.toast(this@DetailActivity,"Cek koneksi internet")
                    }

                })
        }else {
            See.toast(this@DetailActivity,"ID null silahkan coba lagi")
        }


    }
}
package com.example.askdoc.workers
import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.work.ListenableWorker
import androidx.work.WorkerParameters
import androidx.work.impl.utils.futures.SettableFuture
import com.example.askdoc.models.Conseil
import com.example.askdoc.services.RetrofitService
import com.example.askdoc.services.RoomService
import com.google.common.util.concurrent.ListenableFuture
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ConseilWorker(val ctx: Context, val workParamters:
WorkerParameters) : ListenableWorker(ctx, workParamters) {
    lateinit var future: SettableFuture<Result>

    @SuppressLint("RestrictedApi")
    override fun startWork(): ListenableFuture<Result> {
        future = SettableFuture.create()
        val conseils = RoomService.appDatabase.getConseilDao().getConsilsToSynchronize()
        addConseils(conseils)
        return future
    }
    fun addConseils(conseils: List<Conseil>){
        val call = RetrofitService.endpoint.addConseils(conseils)
        call.enqueue(object: Callback<Any> {
            @SuppressLint("RestrictedApi")
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                if(response?.isSuccessful){
                    for (item in conseils) {
                        item.isSyncronized = 1
                    }
                    RoomService.appDatabase.getConseilDao().updateConseils(conseils)
                    future.set(Result.success())
                }else{
                    future.set(Result.retry())
                }
            }

            @SuppressLint("RestrictedApi")
            override fun onFailure(call: Call<Any>, t: Throwable) {
                future.set(Result.retry())
            }
        })
    }
}

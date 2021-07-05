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
        addConseils(conseils[0])
        return future
    }
    fun addConseils(conseil: Conseil){
        val call = RetrofitService.endpoint.addConseil(conseil)
        call.enqueue(object: Callback<String> {
            @SuppressLint("RestrictedApi")
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if(response?.isSuccessful){
                    conseil.isSyncronized=1
                    RoomService.appDatabase.getConseilDao().updateConseil(conseil)
                }else{
                    future.set(Result.retry())
                }
            }

            @SuppressLint("RestrictedApi")
            override fun onFailure(call: Call<String>, t: Throwable) {
                future.set(Result.retry())
            }
        })
    }
}
